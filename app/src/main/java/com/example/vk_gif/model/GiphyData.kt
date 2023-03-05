package com.example.vk_gif.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyData(
    @Json (name = "data") val data: List<GiphyModel>
)