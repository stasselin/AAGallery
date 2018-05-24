package com.duskrunner.aagallery.network

import com.duskrunner.aagallery.model.Collection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/collections/curated")
    fun getCuratedCollections(@Query("client_id") clintId: String): Call<List<Collection>>
}