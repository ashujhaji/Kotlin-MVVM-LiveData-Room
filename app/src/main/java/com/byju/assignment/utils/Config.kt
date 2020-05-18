package com.byju.assignment.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.NonNull

object Config {
    fun urlToImageView(imageView: ImageView, @NonNull imageUrl: String?, context: Context) {
        GlideApp.with(context)
            .load(imageUrl)
            .into(imageView)
    }
}