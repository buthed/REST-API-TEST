package com.example.restapitest.network

import com.example.restapitest.network.model.Ship
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("ships")
    suspend fun fetchShips(): List<Ship>
}