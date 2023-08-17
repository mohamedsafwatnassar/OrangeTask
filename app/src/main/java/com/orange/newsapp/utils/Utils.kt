package com.orange.newsapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Utils {

    fun loadImage(context: Context, urlToImage: String, imgView: ImageView) {
        Glide.with(context)
            .load(urlToImage)
            .into(imgView)
    }
}
