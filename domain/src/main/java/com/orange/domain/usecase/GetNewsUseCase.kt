package com.orange.domain.usecase

import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepo
import com.orange.domain.utils.ResponseHandler

class GetNewsUseCase(private val newsRepo: NewsRepo) {

    suspend operator fun invoke(searchQuery: String): ResponseHandler<NewsResponse?> =
        newsRepo.getNewsFromRemote(searchQuery)

//    operator fun invoke(searchQuery: String): Flow<ResponseHandler<NewsResponse?>> = flow {
//        emit(newsRepo.getNewsFromRemote(searchQuery))
//    }
}
