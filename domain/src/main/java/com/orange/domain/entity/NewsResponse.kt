package com.orange.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "articles")
class NewsResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("articles")
    var articles: List<Article>
)

@Parcelize
@Serializable
data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    // @SerializedName("source")
    // var source: Source,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?
) : Parcelable

@Parcelize
@Serializable
data class Source(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?
) : Parcelable
