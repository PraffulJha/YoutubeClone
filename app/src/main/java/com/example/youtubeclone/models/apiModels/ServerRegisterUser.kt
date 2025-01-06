package com.example.youtubeclone.models.apiModels

import com.example.youtubeclone.db.entity.User

data class ServerRegisterUser(
    val `data`: User,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)