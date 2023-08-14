package com.orange.newsapp.di

import com.orange.data.local.NewsListDao
import com.orange.data.remote.ApiService
import com.orange.data.repositoryImpl.NewsRepoImpl
import com.orange.domain.repository.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepo(apiService: ApiService, newsListDao: NewsListDao): NewsRepo {
        return NewsRepoImpl(apiService, newsListDao)
    }
}
