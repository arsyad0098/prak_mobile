package com.example.arsyadapps.Data.Api

import com.example.arsyadapps.Data.Model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("v2/list")
    suspend fun getPhotos(): List<PhotoModel>
}