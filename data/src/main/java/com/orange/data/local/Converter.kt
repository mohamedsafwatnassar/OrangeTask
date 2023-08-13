package com.orange.data.local

import androidx.room.TypeConverter
import com.orange.domain.entity.Article
import com.orange.domain.entity.Source
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converter {
    @TypeConverter
    fun fromNews(news: List<Article>): String {
        return Json.encodeToString(news)
    }

    @TypeConverter
    fun toNews(string: String): List<Article> {
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun fromSource(source: Source): String {
        return Json.encodeToString(source)
    }

    @TypeConverter
    fun toSource(string: String): Source {
        return Json.decodeFromString(string)
    }
}
