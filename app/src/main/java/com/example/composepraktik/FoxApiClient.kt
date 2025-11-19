package com.example.composepraktik

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoxApiClient {
    val api: RandomFoxApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomfox.ca/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomFoxApi::class.java)
    }
}
