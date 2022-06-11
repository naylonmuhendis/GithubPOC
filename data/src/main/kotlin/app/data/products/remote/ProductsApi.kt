package app.data.products.remote

import app.data.network.BaseApiService
import app.data.network.GenericNetworkResponse
import app.data.products.entity.BeerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi : BaseApiService {


    @GET("beers")
    suspend fun getBeersListByCoroutine(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): List<BeerResponse>

}