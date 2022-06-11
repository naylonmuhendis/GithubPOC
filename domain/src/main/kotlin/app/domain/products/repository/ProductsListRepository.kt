package app.domain.products.repository

import androidx.paging.PagingData
import app.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {


    fun getBeersListByCoroutine(ids: String): Flow<PagingData<Beer>>

}