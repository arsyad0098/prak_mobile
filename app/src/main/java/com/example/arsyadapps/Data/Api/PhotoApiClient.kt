package com.example.arsyadapps.Data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoApiClient {
    // Menggunakan root URL agar lebih fleksibel dengan versi API di Service
    private const val BASE_URL = "https://picsum.photos/"

    val apiService: PhotoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApiService::class.java)
    }
}