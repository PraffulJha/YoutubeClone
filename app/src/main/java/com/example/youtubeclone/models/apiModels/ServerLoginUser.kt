package com.example.youtubeclone.models.apiModels

data class ServerLoginUser(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)