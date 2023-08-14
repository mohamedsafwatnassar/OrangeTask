package com.orange.newsapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Constant {
    const val All = "a"

    const val KEY_LANGUAGE = "language"
    const val KEY_THEME = "theme"
    const val KEY_ARTICLE = "Article"

    const val EN = "en"
    const val AR = "ar"
    const val LIGHT = "light"
    const val DARK = "dark"

    fun loadImage(context: Context, urlToImage: String, imgView: ImageView) {
        Glide.with(context)
            .load(urlToImage)
            .into(imgView)
    }
}
