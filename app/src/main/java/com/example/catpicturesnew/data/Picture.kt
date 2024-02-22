package com.example.catpicturesnew.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Picture (
    @SerializedName("id")
    val id: String,
    @SerializedName("download_url")
    val url: String,
)
