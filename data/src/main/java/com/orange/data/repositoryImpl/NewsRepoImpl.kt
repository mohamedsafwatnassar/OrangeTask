package com.orange.data.repositoryImpl

import com.orange.data.BuildConfig
import com.orange.data.local.NewsListDao
import com.orange.data.remote.ApiService
import com.orange.domain.entity.NewsRequest
import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepo
import com.orange.domain.utils.ResponseHandler

class NewsRepoImpl(
    private val apiService: ApiService,
    private val newsListDao: NewsListDao
) : NewsRepo {

    override suspend fun getNewsFromRemote(): ResponseHandler<NewsResponse?> {
        ResponseHandler.Loading
        return try {
            val response = apiService.getNewsList(
                "apple",
                BuildConfig.API_KEY
            )
            if (response.isSuccessful) {
                insertNewsIntoLocal(response.body()!!)
                ResponseHandler.Success(response.body()!!)
            } else {
                getNewsFromLocal()
            }
        } catch (ex: Exception) {
            getNewsFromLocal()
        }
    }

    private fun insertNewsIntoLocal(list: NewsResponse) {
        newsListDao.insert(list)
    }

    private fun getNewsFromLocal(): ResponseHandler<NewsResponse?> {
        return try {
            ResponseHandler.Success(newsListDao.getNews())
        } catch (error: Exception) {
            ResponseHandler.Error(error.localizedMessage)
        }
    }

    override suspend fun search(
        newsRequest: NewsRequest,
        page: Int
    ): ResponseHandler<NewsResponse?>? {
        return null
    }
}
