package com.example.catpicturesnew.data

import com.example.catpicturesnew.network.PictureService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val repository: PicsumNetworkRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "https://picsum.photos/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PictureService by lazy {
        retrofit.create(PictureService::class.java)
    }

    override val repository: PicsumNetworkRepository by lazy {
        NetworkPicsumRepository(retrofitService)
    }
}