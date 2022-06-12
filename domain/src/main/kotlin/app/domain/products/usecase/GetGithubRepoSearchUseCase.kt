package app.domain.products.usecase

import androidx.paging.PagingData
import app.domain.base.usecase.GeneralUseCase
import app.domain.products.entity.Repo
import app.domain.products.repository.GithubApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGithubRepoSearchUseCase @Inject constructor(
    private val productsListRepository: GithubApiRepository,
) : GeneralUseCase<Flow<PagingData<Repo>>, GetBeersListByCoroutineParams> {

    override fun invoke(params: GetBeersListByCoroutineParams): Flow<PagingData<Repo>> =
        productsListRepository.getKotlinReposSearch(params.searchText)

}

@JvmInline
value class GetBeersListByCoroutineParams(val searchText: String)