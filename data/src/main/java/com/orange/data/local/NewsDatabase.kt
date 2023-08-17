package com.orange.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orange.domain.entity.NewsResponse

@TypeConverters(Converter::class)
@Database(entities = [NewsResponse::class], version = 4)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsListDao(): ArticleDao

    companion object {
        private var instance: NewsDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): NewsDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}
