package com.example.myapplication.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WikiApiServiceProvider{
    val wikiApiService: WikiApiService = retrofit().create(WikiApiService::class.java)


    private fun retrofit(): Retrofit {

        val builder = Retrofit.Builder()
            .baseUrl("https://wiki-api-us.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        builder.client(client)

        return builder.build()
    }
}