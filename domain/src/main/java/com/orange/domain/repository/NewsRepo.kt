package com.orange.domain.repository

import androidx.paging.PagingData
import com.orange.domain.entity.Article
import com.orange.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getNewsFromRemote(searchQuery: String): Flow<PagingData<Article>>

    suspend fun insertNewsIntoLocal(list: NewsResponse)

    suspend fun getNewsFromLocal(): Flow<NewsResponse>
}
