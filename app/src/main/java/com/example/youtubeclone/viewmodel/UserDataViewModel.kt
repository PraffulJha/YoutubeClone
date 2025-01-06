package com.example.youtubeclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeclone.apiServices.result.Result
import com.example.youtubeclone.config.Constansts
import com.example.youtubeclone.models.apiModels.ServerLoginUser
import com.example.youtubeclone.models.apiModels.ServerRegisterUser
import com.example.youtubeclone.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var _loggedInRefreshToken: MutableLiveData<String> = MutableLiveData()
    val loggedInRefreshToken get() = _loggedInRefreshToken as LiveData<*>

    private val _uiState = MutableStateFlow<Result<ServerLoginUser>>(Result.Loading)
    val uiState: StateFlow<Result<ServerLoginUser>> = _uiState

    suspend fun registerUser(body: MultipartBody): Response<ServerRegisterUser> {
        return userRepository.registerUser(body)
    }
    suspend fun loggedInUser(email: String, password: String) {
        try {

            // Create JSON object with email and password

            val jsonObject = JSONObject().apply {
                put(Constansts.EMAIL, email)
                put(Constansts.PASSWORD, password)
            }
            _uiState.value = Result.Loading

            // Create request body
            val body = RequestBody.create(
                "application/json".toMediaTypeOrNull(),
                jsonObject.toString()
            )

            val response = userRepository.loginUser(body)

            when (response.code()) {
                200 -> {
                    _uiState.value = Result.Success(response.body()!!)
                }
                else -> {
                    _uiState.value = Result.Failure(Exception("Login failed with response code: ${response.code()}"))
                }
            }

        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}