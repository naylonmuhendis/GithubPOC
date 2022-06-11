package app.githubpoc.di.module

import app.data.products.datasource.ProductsPagingSourceByCoroutine
import app.data.products.repository.ProductsListRepositoryImpl
import app.domain.products.repository.ProductsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun productsList(
        pagingSourceByCoroutine: ProductsPagingSourceByCoroutine
    ): ProductsListRepository =
        ProductsListRepositoryImpl(pagingSourceByCoroutine)

}