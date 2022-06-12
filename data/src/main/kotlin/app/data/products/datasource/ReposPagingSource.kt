package app.data.products.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.data.products.entity.RepoMapper
import app.data.products.remote.GithubApi
import app.domain.base.Failure
import app.domain.products.entity.Repo
import io.reactivex.rxjava3.annotations.NonNull
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton


private const val STARTING_PAGE_INDEX = 1

@Singleton
class ReposPagingSource @Inject constructor(
    private val productsApi: GithubApi,
    //private val query: String
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return try {
            val response = productsApi.search("kotlin", position).items
                .map {
                    RepoMapper().mapLeftToRight(it)
                }

            toLoadResult(response, position)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }
                is TimeoutException -> {
                    LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }
                else -> {
                    LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }
    }

    override val jumpingSupported = true

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? = state.anchorPosition

    private fun toLoadResult(
        @NonNull response: List<Repo>,
        position: Int,
    ): LoadResult<Int, Repo> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }

}