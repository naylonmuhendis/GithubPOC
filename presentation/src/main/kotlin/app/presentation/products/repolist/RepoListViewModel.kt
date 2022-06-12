package app.presentation.products.repolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import app.domain.products.usecase.GetBeersListByCoroutineParams
import app.domain.products.usecase.GetGithubRepoSearchUseCase
import app.presentation.base.adapter.RecyclerItem
import app.presentation.base.viewmodel.BaseViewModel
import app.presentation.products.entity.RepoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getGithubRepoSearchUseCase: GetGithubRepoSearchUseCase,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    private val _productsListByCoroutine =
        MutableStateFlow<PagingData<RecyclerItem>>(PagingData.empty())
    val productsListByCoroutine: Flow<PagingData<RecyclerItem>> = _productsListByCoroutine


    init {
        getProductsBaseOnPath("")
    }

    private fun getProductsByCoroutinePath(ids: String) =
        getGithubRepoSearchUseCase(GetBeersListByCoroutineParams(searchText = ids))
            .cachedIn(viewModelScope)

    private fun getProductsBaseOnPath(ids: String) {
        viewModelScope.launch {
            _productsListByCoroutine.value = getProductsByCoroutinePath(ids).first()
                .map { beer ->
                    RepoMapper().mapLeftToRight(beer)
                }
        }
    }
}
