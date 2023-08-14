package com.orange.data.repositoryImpl

import android.util.Log
import com.orange.data.local.NewsListDao
import com.orange.data.remote.ApiService
import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepo
import com.orange.domain.utils.ResponseHandler

class NewsRepoImpl(
    private val apiService: ApiService,
    private val newsListDao: NewsListDao
) : NewsRepo {

    override suspend fun getNewsFromRemote(searchQuery: String): ResponseHandler<NewsResponse?> {
        return try {
            val response = apiService.getNewsList(searchQuery)
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
        try {
            newsListDao.insert(list)
        } catch (e: Exception) {
            Log.d("Exception", e.localizedMessage!!)
        }
    }

    private fun getNewsFromLocal(): ResponseHandler<NewsResponse?> {
        return try {
            ResponseHandler.Success(newsListDao.getNews())
        } catch (error: Exception) {
            ResponseHandler.Error(error.localizedMessage)
        }
    }
}
