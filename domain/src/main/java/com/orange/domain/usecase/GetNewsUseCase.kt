package com.orange.domain.usecase

import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepoRemote
import com.orange.domain.utils.ResponseHandler

class GetNewsUseCase(private val newsRepo: NewsRepoRemote) {

    suspend operator fun invoke(): ResponseHandler<NewsResponse?> =
        newsRepo.getNewsRemote()
}
