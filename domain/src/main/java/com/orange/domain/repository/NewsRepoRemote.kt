package com.orange.domain.repository

import com.orange.domain.entity.NewsRequest
import com.orange.domain.entity.NewsResponse
import com.orange.domain.utils.ResponseHandler

interface NewsRepoRemote {

    suspend fun getNewsRemote(): ResponseHandler<NewsResponse?>

    suspend fun search(newsRequest: NewsRequest, page: Int): ResponseHandler<NewsResponse?>
}
