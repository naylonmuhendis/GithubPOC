package app.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.data.products.datasource.ReposPagingSource
import app.domain.extension.allowReads
import app.domain.products.entity.Repo
import app.domain.products.repository.GithubApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepoRepositoryImpl @Inject constructor(
    private val pagingSourceByCoroutine: ReposPagingSource,
) : GithubApiRepository {


    override fun getKotlinReposSearch(searchText: String): Flow<PagingData<Repo>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSourceByCoroutine }
            ).flow
        }
}