package com.byju.assignment.data.remote

/*MIT License

        Copyright (c) 2020 Ashutosh Jha

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.*/

import android.content.Context
import com.byju.assignment.BuildConfig
import com.byju.assignment.model.TopHeadlineResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(context: Context) {
    private lateinit var apiService: ApiService

    init {
        val retrofit = initRetrofit(context)
        initServices(retrofit)
    }

    private fun initRetrofit(context: Context): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .apply {
                networkInterceptors().add(Interceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                    chain.proceed(request)
                })
                if (BuildConfig.DEBUG)
                    addInterceptor(interceptor)
                addInterceptor(ConnectivityAwareClient(context))
            }

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }


    private fun initServices(retrofit: Retrofit?) {
        if (retrofit != null)
            apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getTopHeadlines(): Response<TopHeadlineResponse> {
        return apiService.getHeadlines("in", BuildConfig.API_KEY)
    }


}