package com.example.frontend.repository

import com.example.frontend.apicall.ApiInterface
import com.example.frontend.apicall.responseModel.ResponseLoginUser
import com.example.frontend.apicall.responseModel.ServerRegisterUser
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : ApiRepository {


    override suspend fun getLogin(body: MultipartBody?) : Response<ResponseLoginUser> {
        return apiInterface.loginUser("multipart/form-data; boundary=" + body?.boundary, body).awaitResponse()
    }

    override suspend fun registerUser(body: MultipartBody?): Response<ServerRegisterUser> {
        return apiInterface.registerUser("multipart/form-data; boundary=" + body?.boundary, body).awaitResponse()
    }



    override suspend fun uploadAvatar() {

    }


}