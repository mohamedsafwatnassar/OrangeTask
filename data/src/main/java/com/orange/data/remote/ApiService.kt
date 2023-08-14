package com.orange.data.remote

import com.orange.data.BuildConfig
import com.orange.domain.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiService {

    @GET("everything")
    suspend fun getNewsList(
        @Query("q") searchQuery: String,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponse>
}
