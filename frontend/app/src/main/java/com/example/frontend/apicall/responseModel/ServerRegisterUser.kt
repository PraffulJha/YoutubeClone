package com.example.frontend.apicall.responseModel

import android.service.autofill.UserData

data class ServerRegisterUser(
    val `data`: UserData,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)