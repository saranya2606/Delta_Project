package com.example.maps

import android.text.Editable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {


        @GET("place/nearbysearch/json")
        fun getNearbyHotels(
            @Query("location") location: String,
            @Query("radius") radius: Int,
            @Query("type") type: String,
            @Query("key") apiKey: String
        ): Call<ApiResponse>

    @GET("geocode/json")
    fun searchLocation(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): Call<GeoResponse>

    @GET("geocode/json")
    fun geoAddress(
        @Query("address") address: Editable,
        @Query("key") apiKey: String
    ): Call<GeoResponse>




}
