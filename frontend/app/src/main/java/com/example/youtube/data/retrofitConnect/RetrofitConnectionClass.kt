package com.example.youtube.data.repository

import com.example.youtube.data.apiCall.UserRegisterApi
import com.example.youtube.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnectionClass {


    fun getUserRegisterAPi() : UserRegisterApi {

        val gson = GsonBuilder()
            .setLenient()
            .create()
       val retrofit = Retrofit.Builder()
           .baseUrl(Constants.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create(gson))
           .build()
        return retrofit.create(UserRegisterApi::class.java)
    }
}