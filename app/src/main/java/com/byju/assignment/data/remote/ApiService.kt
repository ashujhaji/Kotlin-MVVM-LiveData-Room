package com.byju.assignment.data.remote

import com.byju.assignment.model.TopHeadlineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(ApiConstant.TOP_HEADLINES)
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<TopHeadlineResponse>
}