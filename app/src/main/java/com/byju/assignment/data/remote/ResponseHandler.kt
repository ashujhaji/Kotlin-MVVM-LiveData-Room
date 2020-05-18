package com.byju.assignment.data.remote


import android.content.Context
import retrofit2.Response

class ResponseHandler<out T>(private val response: Response<T>) {

    fun handleResponse(context: Context): T? {
        if (response.isSuccessful && response.code() == 200) {
            return response.body()
        } else {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
            when (response.code()) {
                401 -> {

                    return null
                }
                else -> return null
            }
        }

    }
}