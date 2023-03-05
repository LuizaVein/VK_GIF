package com.example.vk_gif.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GiphyModel(
    @Json(name = "id") val id: String,
    @Json(name = "embed_url") val url: String,
    @Json(name = "images") val images: GiphyImages,
    @Json(name = "username") val username: String,
    @Json(name = "rating") val rating: String,
    @Json(name = "create_datetime") val createDatetime: String?,
    @Json(name = "title") val title: String,
    @Json(name = "alt_text") val altText: String?
): Parcelable