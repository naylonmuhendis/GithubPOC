package app.domain.products.repository

import androidx.paging.PagingData
import app.domain.products.entity.Repo
import kotlinx.coroutines.flow.Flow

interface GithubApiRepository {


    fun getKotlinReposSearch(searchText: String = "Kotlin"): Flow<PagingData<Repo>>

}