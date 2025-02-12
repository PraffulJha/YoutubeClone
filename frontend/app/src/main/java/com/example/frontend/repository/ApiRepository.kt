package com.example.frontend.repository

import com.example.frontend.apicall.responseModel.ResponseLoginUser
import com.example.frontend.apicall.responseModel.ServerRegisterUser
import okhttp3.MultipartBody
import retrofit2.Response

interface ApiRepository {
    suspend fun  getLogin( body: MultipartBody?) : Response<ResponseLoginUser>
    suspend fun registerUser(body: MultipartBody?) : Response<ServerRegisterUser>
    suspend fun uploadAvatar()
}