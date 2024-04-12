package com.matrix159.thecatapp.data.di

import com.matrix159.thecatapp.data.BuildConfig
import com.matrix159.thecatapp.data.CatsApiRemoteDataSource
import com.matrix159.thecatapp.data.CatsRemoteDataSource
import com.matrix159.thecatapp.data.DefaultCatsRepository
import com.matrix159.thecatapp.data.createCatApiClient
import com.matrix159.thecatapp.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    internal fun provideCatApiClient(): HttpClient = createCatApiClient(BuildConfig.CATS_API_KEY)

    @Provides
    @Singleton
    internal fun provideCatRemoteDataSource(httpClient: HttpClient): CatsRemoteDataSource =
        CatsApiRemoteDataSource(httpClient)

    @Provides
    @Singleton
    internal fun provideCatsRepository(catsRemoteDataSource: CatsRemoteDataSource): CatsRepository =
        DefaultCatsRepository(catsRemoteDataSource)
}