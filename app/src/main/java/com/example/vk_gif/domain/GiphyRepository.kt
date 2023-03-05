package com.example.vk_gif.domain

import androidx.paging.PagingData
import com.example.vk_gif.model.GiphyModel
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {
    fun getGyphs(query: String): Flow<PagingData<GiphyModel>>
}