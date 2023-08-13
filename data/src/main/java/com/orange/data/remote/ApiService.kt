package com.orange.data.remote

import com.orange.domain.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNewsList(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}
