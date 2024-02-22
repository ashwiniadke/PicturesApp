package com.example.catpicturesnew.data

import com.example.catpicturesnew.network.PictureService


interface PicsumNetworkRepository {
    suspend fun getPictures(page: Int, perPage: Int): List<Picture>
}

class NetworkPicsumRepository(
    private val picService: PictureService
): PicsumNetworkRepository {

    override suspend fun getPictures(page: Int, perPage: Int): List<Picture>
    = picService.pictures(page, perPage)
}