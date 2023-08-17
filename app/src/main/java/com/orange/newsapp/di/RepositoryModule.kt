package com.orange.newsapp.di

import com.orange.data.local.ArticleDao
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
    fun provideRepo(apiService: ApiService, newsListDao: ArticleDao): NewsRepo {
        return NewsRepoImpl(apiService, newsListDao)
    }
}
