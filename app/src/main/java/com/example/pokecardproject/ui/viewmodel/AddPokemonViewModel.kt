package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.*
import kotlinx.coroutines.launch

class AddPokemonViewModel(
    private val userRepository: UserRepository,
    private val competenceRepository: CompetenceRepository,
    private val pokemonDBRepository: PokemonDBRepository
) : ViewModel() {

    var currentUser: User? = null
    var pokemonToAdd: PokemonDB? = null

    fun getUser(userId: Long, onSuccess: OnSuccess<User>) {
        viewModelScope.launch {
            userRepository.getUserById(userId)?.run(onSuccess)
        }
    }

    fun getAllCompetences(onSuccess: OnSuccess<List<Competence>>) {
        viewModelScope.launch {
            competenceRepository.getAll()?.run(onSuccess)
        }
    }

    fun insertPokemonDb(pokemonDB: PokemonDB, onSuccess: OnSuccess<Long>) {
        viewModelScope.launch {
            pokemonDBRepository.insertPokemonDB(pokemonDB).run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddPokemonViewModel(
                UserRepository.instance,
                CompetenceRepository.instance,
                PokemonDBRepository.instance
            ) as T
        }
    }
}