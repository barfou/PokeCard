package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.DetailPokemonRepository
import com.example.pokecardproject.data.repository.ListPokemonRepository
import com.example.pokecardproject.data.repository.UserRepository
import kotlinx.coroutines.launch
import java.net.URL

class MainActivityViewModel(
    private val userRepository: UserRepository,
    private val detailPokemonRepository: DetailPokemonRepository,
    private val listPokemonRepository: ListPokemonRepository
) : ViewModel() {

    var currentUser: User? = null
    var listPokemon: List<PokemonBase>? = null

    private var _data = mutableListOf<Int>()

    val data: List<Int>
        get() = _data

    val pokemonsPagedList = listPokemonRepository.getPaginatedList(viewModelScope)


    fun getListPokemons(onSuccess: OnSuccess<List<PokemonBase>>) {

        if (listPokemon != null) {
            onSuccess(listPokemon!!)
        } else {
            viewModelScope.launch {
                listPokemonRepository.getListPokemons()?.run {
                    listPokemon = this
                    onSuccess(listPokemon!!)
                }
            }
        }
    }

    fun getPokemonDetails(name: String, onSuccess: OnSuccess<PokemonInfo?>) {
        viewModelScope.launch {
            detailPokemonRepository.getDetailsPokemon(name).run(onSuccess)
        }
    }

    fun getUser(userId: Long, onSuccess: OnSuccess<User>) {
        viewModelScope.launch {
            userRepository.getUserById(userId)?.run(onSuccess)
        }
    }

    fun getPokemonDetailsDirect(url: String, onSuccess: OnSuccess<PokemonInfo?>) {
        viewModelScope.launch {
            detailPokemonRepository.getDetailsPokemonDirect(url).run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(
                UserRepository.instance,
                DetailPokemonRepository.instance,
                ListPokemonRepository.instance
            ) as T
        }
    }
}