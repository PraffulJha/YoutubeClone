package com.example.frontend.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.frontend.repository.ApiRepository

@HiltViewModel
class UserApiViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {


}