package com.example.frontend.apicall.responseModel

data class ResponseLoginUser(
    val `data`: LoginData,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)