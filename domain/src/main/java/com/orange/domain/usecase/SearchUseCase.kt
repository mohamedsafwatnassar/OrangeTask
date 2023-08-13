package com.orange.domain.usecase

import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepo
import com.orange.domain.utils.ResponseHandler

class SearchUseCase(private val newsRepo: NewsRepo) {

    suspend operator fun invoke(): ResponseHandler<NewsResponse?> =
        newsRepo.getNewsFromRemote()
}
