package com.example.vk_gif.view.search.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_gif.domain.GiphyInteractor
import com.example.vk_gif.model.GiphyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val giphyInteractor: GiphyInteractor): ViewModel() {

    fun createPaging(query: String = ""): Flow<PagingData<GiphyModel>> {
        return giphyInteractor.getTrending(
            query = query
        ).cachedIn(viewModelScope)
    }
}