package com.byju.assignment.data.remote

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