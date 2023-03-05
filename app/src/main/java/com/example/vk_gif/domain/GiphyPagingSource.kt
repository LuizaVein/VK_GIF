package com.example.vk_gif.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_gif.domain.api.GiphyApi
import com.example.vk_gif.model.GiphyModel

const val NETWORK_PAGE_SIZE = 25
private const val INITIAL_LOAD_SIZE = 1

class GiphyPagingSource(private val api: GiphyApi, private val query: String = ""): PagingSource<Int, GiphyModel>() {

    override fun getRefreshKey(state: PagingState<Int, GiphyModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyModel> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val response = if (query.isEmpty()) {
                api.getTrending(offset = offset, limit = params.loadSize)
            } else {
                api.getSearch(query = query, offset = offset, limit = params.loadSize)
            }
            val nextKey = if (response.data.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}