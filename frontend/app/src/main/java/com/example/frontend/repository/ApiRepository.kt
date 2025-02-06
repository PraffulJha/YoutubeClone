package com.example.frontend.repository

import com.example.frontend.apicall.responseModel.ResponseLoginUser
import okhttp3.MultipartBody
import retrofit2.Response

interface ApiRepository {
    suspend fun  getLogin( body: MultipartBody?) : Response<ResponseLoginUser>
    suspend fun registerUser()
    suspend fun uploadAvatar()
}