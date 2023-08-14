package com.orange.newsapp.di

import com.orange.domain.repository.NewsRepo
import com.orange.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(newsRepo: NewsRepo): GetNewsUseCase {
        return GetNewsUseCase(newsRepo)
    }
}
