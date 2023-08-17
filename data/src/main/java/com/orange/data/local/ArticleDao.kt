package com.orange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orange.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert
    fun insert(newsResponse: NewsResponse)

    @Query("Select * from articles")
    fun getNews(): Flow<NewsResponse>
}
