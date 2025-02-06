package com.example.frontend.apicall.responseModel

data class LoginData(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)