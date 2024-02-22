package com.example.catpicturesnew.network

import com.example.catpicturesnew.data.Picture
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureService {

    @GET("list")
    suspend fun pictures(
        @Query("page") page: Int = 1,
        @Query("limit") perPage: Int = PER_PAGE_COUNT
    ): List<Picture>

    companion object {
        private const val PER_PAGE_COUNT = 30
    }
}
