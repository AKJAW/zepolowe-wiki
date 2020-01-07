package com.example.myapplication.network

import com.example.myapplication.entities.WikiApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {
    @GET("random-article")
    suspend fun randomArticle(): WikiApiResponse

    @GET("article-from-title")
    suspend fun articleFromTitle(@Query("title") title: String): WikiApiResponse
}
