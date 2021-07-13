package com.example.quakeinfo.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuakeFeatures(
    @Json(name = "properties") val properties: QuakeFeaturesProperties,
    @Json(name = "id") val id: String
)
