package com.example.frontend

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("_id")
    val id: String,

    @SerializedName("videoFile")
    val videoFile: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("views")
    val views: Int = 0,

    @SerializedName("isPublished")
    val isPublished: Boolean = true,

    @SerializedName("owner")
    val owner: String?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)
