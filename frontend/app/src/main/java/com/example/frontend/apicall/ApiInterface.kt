package com.example.frontend.apicall

import com.example.frontend.apicall.responseModel.ResponseLoginUser
import com.example.frontend.apicall.responseModel.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST


interface ApiInterface {

    @GET("login")
    fun loginUser(@Header("Content-Type") contentType: String?,
        @Body body: MultipartBody?) : Call<ResponseLoginUser>

    @POST("register")
    fun registerUser(@Header("Content-Type") contentType: String?,
                     @Body body: MultipartBody?) : Call<User>
    @PATCH("avatar")
    fun changeAvatar(@Header("Content-Type") contentType: String?,
                     @Body body: MultipartBody?)


}