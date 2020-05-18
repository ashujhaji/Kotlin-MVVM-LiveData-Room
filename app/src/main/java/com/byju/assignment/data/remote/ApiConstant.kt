package com.byju.assignment.data.remote

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

object ApiConstant {

    //endpoints
    const val TOP_HEADLINES = "top-headlines"

    val context = Dispatchers.IO + CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
    }
}