package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.*
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val userRepository: UserRepository,
    private val detailPokemonRepository: DetailPokemonRepository,
    private val listPokemonRepository: ListPokemonRepository,
    private val competenceRepository: CompetenceRepository,
    private val pokemonDBRepository: PokemonDBRepository
) : ViewModel() {

    var currentUser: User? = null

    val pokemonsPagedList = listPokemonRepository.getPaginatedList(viewModelScope)

    fun getPokemonDetails(pokemonBase: PokemonBase, onSuccess: OnSuccess<PokemonInfo?>) {
        viewModelScope.launch {
            detailPokemonRepository.getDetailsPokemon(pokemonBase).run(onSuccess)
        }
    }

    fun getUser(userId: Long, onSuccess: OnSuccess<User>) {
        viewModelScope.launch {
            userRepository.getUserById(userId)?.run(onSuccess)
        }
    }

    fun initTableCompetence() {
        viewModelScope.launch {
            competenceRepository.initTable()
        }
    }

    fun updateUser(login: String, mail: String, password: String, id: Long) {
        viewModelScope.launch {
            userRepository.updateUser(login, mail, password, id)
            // MAJ value in VM
            getUser(id) {
                currentUser = it
            }
        }
    }

    fun getAllPokemonDbWithListCompetences(userId: Long, onSuccess: OnSuccess<List<PokemonDB>>) {
        viewModelScope.launch {
            var listPokemonDb = pokemonDBRepository.getAllPokemonsOfUser(userId)
            listPokemonDb?.forEach {
                val listCompetences = competenceRepository.getCompetencesWithPokemonDbId(it.id)
                it.competences = listCompetences ?: emptyList()
            }
            listPokemonDb?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(
                UserRepository.instance,
                DetailPokemonRepository.instance,
                ListPokemonRepository.instance,
                CompetenceRepository.instance,
                PokemonDBRepository.instance
            ) as T
        }
    }
}