package app.data.products.remote

import app.data.network.BaseApiService
import app.data.products.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi : BaseApiService {

    @GET("search/repositories")
    suspend fun search(
        @Query("q") q: String,
        @Query("page") pageIndex: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): SearchResponse

}