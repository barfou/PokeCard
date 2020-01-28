package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokecardproject.data.repository.UserRepository

class ConnexionViewModel (
    private val userRepository: UserRepository
): ViewModel() {

    fun credentialsOk(login: String, password: String, onSuccess: OnSuccess<Boolean>) {
        viewModelScope.launch {
            userRepository.credentialsOk(login, password)?.run(onSuccess)
        }
    }

    companion object Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ConnexionViewModel(UserRepository.instance) as T
        }
    }
}


