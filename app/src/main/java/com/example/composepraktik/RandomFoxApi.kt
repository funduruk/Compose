package com.example.composepraktik

import retrofit2.http.GET

interface RandomFoxApi {
    @GET("floof/")
    suspend fun getRandomFox(): Fox
}
