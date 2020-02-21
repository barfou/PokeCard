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

    /*fun pingIp(): Boolean {

        viewModelScope.launch {
            return withContext(Dispatchers.IO) {
                if (InetAddress.getByName("192.168.240.67").isReachable(2000)) {
                    System.out.println("visible")
                    return@withContext true
                } else {
                    System.out.println("not visible")
                    return@withContext false
                }
            }
        }
    }*/

    companion object Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ConnexionViewModel(UserRepository.instance) as T
        }
    }
}


