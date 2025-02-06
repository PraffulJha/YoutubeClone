package com.example.frontend.apicall.responseModel

import com.example.frontend.Video

data class User(
    val __v: Int,
    val _id: String,
    val avatar: String,
    val coverimg: String,
    val createdAt: String,
    val email: String,
    val fullname: String,
    val refershToken: String,
    val updatedAt: String,
    val username: String,
    val watchHistory: List<Video>
)