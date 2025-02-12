package com.example.frontend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.Utility.Constants.TAG_HTTP
import com.example.frontend.Utility.Utility
import com.example.frontend.apicall.Results
import com.example.frontend.apicall.responseModel.ServerRegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.frontend.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File

@HiltViewModel
class UserApiViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _registerResult = MutableStateFlow<Results<ServerRegisterUser>>(Results.Loading)
    val registerResult = _registerResult.asStateFlow()

    fun registerUser(name: String,email : String,username:String,password: String,avatarFile : File) {

        viewModelScope.launch(Dispatchers.IO) {
            val part = Utility.prepareFilePart("file",avatarFile)
            val body = MultipartBody.Builder()
                .addFormDataPart("fullName",name)
                .addFormDataPart("email",email)
                .addFormDataPart("username",username)
                .addFormDataPart("password",password)
                .addPart(part)
                .build()
            val response = apiRepository.registerUser(body)
            if(response.isSuccessful){
                when(response.code()){
                    200,201 -> if(response.body() != null){
                        _registerResult.value = Results.Success(response.body()!!)
                    }
                    401 -> _registerResult.value = Results.Error(response.errorBody()!!.toString())
                }
            } else {
                Log.d(TAG_HTTP,response.errorBody().toString())
            }


        }
    }


}