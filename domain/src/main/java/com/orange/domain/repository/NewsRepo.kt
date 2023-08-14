package com.orange.domain.repository

import com.orange.domain.entity.NewsResponse
import com.orange.domain.utils.ResponseHandler

interface NewsRepo {

    suspend fun getNewsFromRemote(searchQuery: String): ResponseHandler<NewsResponse?>
}
