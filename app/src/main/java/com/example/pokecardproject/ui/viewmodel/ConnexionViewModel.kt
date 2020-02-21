package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress

class ConnexionViewModel (
    private val userRepository: UserRepository
): ViewModel() {

    fun credentialsOk(login: String, password: String, onSuccess: OnSuccess<User?>) {
        viewModelScope.launch {
            userRepository.credentialsOk(login, password).run(onSuccess)
        }
    }

    companion object Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ConnexionViewModel(UserRepository.instance) as T
        }
    }
}


