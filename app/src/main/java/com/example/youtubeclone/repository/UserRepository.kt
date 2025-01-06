package com.example.youtubeclone.repository

import com.example.youtubeclone.apiServices.apiCalls.ApiServices
import com.example.youtubeclone.models.apiModels.ServerLoginUser
import com.example.youtubeclone.models.apiModels.ServerRegisterUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class UserRepository @Inject constructor (private val apiServices: ApiServices) {

    suspend fun registerUser(body: MultipartBody?) : Response<ServerRegisterUser> {
        return apiServices.register("multipart/form-data; boundary=" + body?.boundary, body).execute()
    }
    suspend fun loginUser(body: RequestBody) : Response<ServerLoginUser>{
        return apiServices.loginUser(   "application/json",body).awaitResponse()
    }
}