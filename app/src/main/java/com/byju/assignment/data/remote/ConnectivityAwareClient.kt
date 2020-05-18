package com.byju.assignment.data.remote

import android.content.Context
import com.byju.assignment.helper.NetworkConnectivityHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityAwareClient(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkConnectivityHelper.isNetworkAvailable(context)) {
            throw NetworkException()
        } else return chain.proceed(chain.request())
    }
}

class NetworkException: IOException() {
    override fun getLocalizedMessage(): String {
        return "No network available."
    }
}