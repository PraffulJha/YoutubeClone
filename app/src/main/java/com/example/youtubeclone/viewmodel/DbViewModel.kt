package com.example.youtubeclone.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeclone.db.entity.User
import com.example.youtubeclone.repository.UserDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DbViewModel @Inject constructor(private val userDbRepository: UserDbRepository)  : ViewModel() {

    private var _loggedInUser : MutableLiveData<User> = MutableLiveData()
    val loggedInUser get() = _loggedInUser as LiveData<User?>

    fun insertUser(uSerData: User){
        viewModelScope.launch {
            userDbRepository.insertUser(uSerData)
        }
    }

    suspend fun getUserFromId(id: String) : User{
       val user =  viewModelScope.async {
            userDbRepository.getUserFromId(id)
        }
        return user.await()
    }

    suspend fun getUserFromRefreshToken(refreshToken: String) : User{
        val user =  viewModelScope.async {
            userDbRepository.getUSerFromRefreshToken(refreshToken)
        }
        return user.await()
    }
}