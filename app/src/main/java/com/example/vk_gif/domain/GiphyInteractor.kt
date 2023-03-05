package com.example.vk_gif.domain

import androidx.paging.PagingData
import com.example.vk_gif.model.GiphyModel
import kotlinx.coroutines.flow.Flow

class GiphyInteractor(private val giphyRepository: GiphyRepository) {
    fun getTrending(query: String = ""): Flow<PagingData<GiphyModel>> {
        return giphyRepository.getGyphs(query)
    }
}