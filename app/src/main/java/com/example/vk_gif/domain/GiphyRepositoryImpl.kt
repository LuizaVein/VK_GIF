package com.example.vk_gif.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vk_gif.domain.api.GiphyApi
import com.example.vk_gif.model.GiphyModel
import kotlinx.coroutines.flow.Flow

class GiphyRepositoryImpl (private val giphyApi: GiphyApi): GiphyRepository {
    override fun getGyphs(query: String): Flow<PagingData<GiphyModel>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GiphyPagingSource(query = query, api = giphyApi)
        }
    ).flow
}
