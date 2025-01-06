package com.example.youtubeclone.apiServices.apiCalls

import com.example.youtubeclone.models.apiModels.ServerLoginUser
import com.example.youtubeclone.models.apiModels.ServerRegisterUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiServices {

    @POST("/register")
    suspend fun register(@Header("Content-Type") contentType: String?,
                         @Body body: MultipartBody?) : retrofit2.Call<ServerRegisterUser>

    @POST("login")
    fun loginUser(@Header("Content-Type") contentType: String?,
                          @Body body: RequestBody?) : Call<ServerLoginUser>

    @POST("/refersh-token")
    suspend fun refreshToken(@Header("Content-Type") contentType: String?,
                             @Body body: MultipartBody?)

    @GET("/current-user")
    suspend fun getCurrentUser(@Header("Content-Type") contentType: String?,
                               @Body body: MultipartBody?)

    @PATCH("/update-account-details")
    suspend fun updateAccountDetails(@Header("Content-Type") contentType: String?,
                                     @Body body: MultipartBody?)

    @PATCH("/update-avatar")
    suspend fun updateAvatar(@Header("Content-Type") contentType: String?,
                             @Body body: MultipartBody?)
    @PATCH("/update_coverImg")
    suspend fun updateCoverImg(@Header("Content-Type") contentType: String?,
                               @Body body: MultipartBody?)
    @GET("/c/:username")
    suspend fun updateChannelProfile(@Header("Content-Type") contentType: String?,
                                     @Body body: MultipartBody?)
    @GET("/watchHistory")
    suspend fun getWatchHistory(@Header("Content-Type") contentType: String?,
                                @Body body: MultipartBody?)

}