package com.orange.domain.repository

import com.orange.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepoLocal {

    suspend fun insertNewsLocal(list: NewsResponse)

    suspend fun getNewsLocal(): Flow<NewsResponse>
}
