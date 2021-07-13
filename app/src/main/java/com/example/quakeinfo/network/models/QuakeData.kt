package com.example.quakeinfo.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuakeData(
    @Json(name = "metadata") val metadata: QuakeMetaData,
    @Json(name = "features") val features: List<QuakeFeatures>
)
