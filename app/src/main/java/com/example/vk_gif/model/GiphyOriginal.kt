package com.example.vk_gif.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GiphyOriginal(
    @Json(name = "url") val url: String
): Parcelable