package app.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import app.domain.products.usecase.GetBeersListByCoroutineParams
import app.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.presentation.base.adapter.RecyclerItem
import app.presentation.base.viewmodel.BaseViewModel
import app.presentation.products.entity.BeerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase,
    savedStateHandle: SavedStateHandle,
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
        getBeersListByCoroutineUseCase(GetBeersListByCoroutineParams(ids = ids))
            .cachedIn(viewModelScope)

    private fun getProductsBaseOnPath(ids: String) {
        viewModelScope.launch {
            _productsListByCoroutine.value = getProductsByCoroutinePath(ids).first()
                .map { beer ->
                    BeerMapper().mapLeftToRight(beer)
                }
        }
    }
}
