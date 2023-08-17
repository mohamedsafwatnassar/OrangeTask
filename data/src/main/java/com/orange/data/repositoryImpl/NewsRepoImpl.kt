package com.orange.data.repositoryImpl

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orange.data.local.ArticleDao
import com.orange.data.paging.NewsPagingSource
import com.orange.data.remote.ApiService
import com.orange.domain.entity.Article
import com.orange.domain.entity.NewsResponse
import com.orange.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) : NewsRepo {

    override fun getNewsFromRemote(
        searchQuery: String
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(apiService, searchQuery) }
        ).flow
    }

    override suspend fun insertNewsIntoLocal(list: NewsResponse) {
        articleDao.insert(list)
    }

    override suspend fun getNewsFromLocal(): Flow<NewsResponse> {
        return articleDao.getNews()
    }
}
