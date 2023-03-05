package com.example.vk_gif.domain.api

import com.example.vk_gif.model.GiphyData
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("trending")
    suspend fun getTrending(@Query("api_key") apiKey: String = API_KEY,
                            @Query("offset") offset: Int = 0,
                            @Query("limit") limit: Int = 25): GiphyData

    @GET("search")
    suspend fun getSearch(
        @Query("q") query: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 25,
    ): GiphyData

    companion object {
        private const val API_KEY = "s6raYjsfM1WkD7ABVFQIHJRqKUNps2ED"
    }
}