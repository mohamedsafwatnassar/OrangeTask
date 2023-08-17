package com.orange.domain.usecase

import androidx.paging.PagingData
import com.orange.domain.entity.Article
import com.orange.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(private val newsRepo: NewsRepo) {

    operator fun invoke(searchQuery: String): Flow<PagingData<Article>> {
        return newsRepo.getNewsFromRemote(searchQuery)
    }
}


