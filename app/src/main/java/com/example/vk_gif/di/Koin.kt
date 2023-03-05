package com.example.vk_gif.di

import com.example.vk_gif.domain.GiphyInteractor
import com.example.vk_gif.domain.GiphyRepository
import com.example.vk_gif.domain.GiphyRepositoryImpl
import com.example.vk_gif.view.detail.vm.DetailViewModel
import com.example.vk_gif.view.main.vm.MainViewModel
import com.example.vk_gif.view.search.vm.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single {
        provideMoshi()
    }
    single {
        provideGyphyHttpClient()
    }
    single {
        provideGyphyApi(
            baseUrl = "https://api.giphy.com/v1/gifs/",
            moshi = get(),
            okHttpClient = get()
        )
    }

    singleOf(::GiphyRepositoryImpl) { bind<GiphyRepository>() }
    viewModelOf(::MainViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailViewModel)
    singleOf(::GiphyInteractor)

}