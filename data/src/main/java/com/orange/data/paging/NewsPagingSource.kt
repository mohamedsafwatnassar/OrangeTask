package com.orange.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orange.data.remote.ApiService
import com.orange.domain.entity.Article

class NewsPagingSource(
    private val apiService: ApiService,
    private val searchQuery: String
) :
    PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val response = apiService.getNewsList(searchQuery, page, pageSize)
            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.body()!!.articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}
