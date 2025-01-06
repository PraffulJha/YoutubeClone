package com.example.youtubeclone.models.apiModels

data class User(
    val __v: Int,
    val _id: String,
    val avatar: String,
    val coverimg: String,
    val createdAt: String,
    val email: String,
    val fullname: String,
    val updatedAt: String,
    val username: String,
    val watchHistory: List<Any>
)