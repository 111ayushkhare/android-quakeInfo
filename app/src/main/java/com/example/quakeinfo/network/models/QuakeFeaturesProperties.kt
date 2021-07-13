package com.example.quakeinfo.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuakeFeaturesProperties(
    @Json(name = "mag") val mag: String,
    @Json(name = "place") val place: String?,
    @Json(name = "time") val time: Long,
    @Json(name = "url") val url: String,
    @Json(name = "detail") val detail: String,
    @Json(name = "title") val title: String
)
