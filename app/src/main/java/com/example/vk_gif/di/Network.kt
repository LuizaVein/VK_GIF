package com.example.vk_gif.di

import com.example.vk_gif.domain.api.GiphyApi
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit


fun getLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val defaultHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder().apply {
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        connectTimeout(15, TimeUnit.SECONDS)
    }.build()
}

fun provideGyphyHttpClient(): OkHttpClient {
    return defaultHttpClient.newBuilder().apply {
        addInterceptor(getLoggingInterceptor())
    }.build()
}

fun provideGyphyApi(
    baseUrl: String,
    moshi: Moshi,
    okHttpClient: OkHttpClient
): GiphyApi {
    val retrofit = Retrofit.Builder().apply {
        addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        client(okHttpClient)
        baseUrl(baseUrl)
    }.build()

    return retrofit.create()
}

fun provideMoshi(): Moshi =
    Moshi.Builder().apply {
    }.build()