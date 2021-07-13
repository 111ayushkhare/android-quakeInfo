package com.example.quakeinfo.network

import com.example.quakeinfo.network.models.QuakeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://earthquake.usgs.gov/"

// Setting up Moshi convertor to decode fetched JSON data
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Setting up retrofit for api fetching
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

// Interface defining the get request
interface QuakeApiService {
    @GET("fdsnws/event/1/query")
    suspend fun getQuakeInfo(
        @Query("format") format: String = "geojson",
        @Query("minmagnitude") minmagnitude: Double,
        @Query("maxmagnitude") maxmagnitude: Double
    ): QuakeData
}

object QuakeApi {
    val retrofitService: QuakeApiService by lazy { retrofit.create(QuakeApiService::class.java) }
}