package com.example.quakeinfo.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuakeMetaData(
    @Json(name = "url") val url: String,
    @Json(name = "title") val title: String,
    @Json(name = "status") val status: Int
)
