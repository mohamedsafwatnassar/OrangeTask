package com.orange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orange.domain.entity.NewsResponse

@Dao
interface NewsListDao {

    @Insert
    fun insert(newsResponse: NewsResponse)

    @Query("Select * from articles")
    fun getNews(): NewsResponse
}
