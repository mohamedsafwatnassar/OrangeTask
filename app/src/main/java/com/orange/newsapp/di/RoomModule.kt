package com.orange.newsapp.di

import android.content.Context
import com.orange.data.local.NewsDatabase
import com.orange.data.local.NewsListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideNewsDao(@ApplicationContext context: Context): NewsListDao {
        return NewsDatabase.getInstance(context).newsListDao()
    }
}
