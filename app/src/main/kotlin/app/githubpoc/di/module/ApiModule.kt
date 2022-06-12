package app.githubpoc.di.module

import app.data.products.remote.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun products(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

}