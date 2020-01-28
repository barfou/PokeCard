package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegistrationViewModel (
    private val userRepository: UserRepository
): ViewModel() {

    fun insertUser(user: User, onSuccess: OnSuccess<Boolean>) {
        viewModelScope.launch {
            userRepository.insertUser(user).run(onSuccess)
        }
    }

    fun loginExist(login: String, onSuccess: OnSuccess<Boolean>) {
        viewModelScope.launch {
            userRepository.loginExist(login)?.run(onSuccess)
        }
    }

    companion object Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RegistrationViewModel(UserRepository.instance) as T
        }
    }
}