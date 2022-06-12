package app.githubpoc.di.module

import app.data.products.datasource.ReposPagingSource
import app.data.products.repository.GitRepoRepositoryImpl
import app.domain.products.repository.GithubApiRepository
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
        pagingSourceByCoroutine: ReposPagingSource
    ): GithubApiRepository =
        GitRepoRepositoryImpl(pagingSourceByCoroutine)

}