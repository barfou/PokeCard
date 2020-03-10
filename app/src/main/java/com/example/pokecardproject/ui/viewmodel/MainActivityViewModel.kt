package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.data.repository.*
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

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

    fun loginExist(login: String, onSuccess: OnSuccess<Boolean>) {
        viewModelScope.launch {
            userRepository.loginExist(login)?.run(onSuccess)
        }
    }

    fun updateUser(
        login: String,
        mail: String,
        password: String,
        id: Long,
        onSuccess: OnSuccess<Boolean>
    ) {
        viewModelScope.launch {
            userRepository.updateUser(login, mail, password, id).run(onSuccess)
            // MAJ value in ViewModel
            getUser(id) {
                currentUser = it
            }
        }
    }

    private fun getAll(userId: Long, onSuccess: OnSuccess<List<PokemonDB>>) {
        viewModelScope.launch {
            pokemonDBRepository.getAllPokemonsOfUser(userId)?.run(onSuccess)
        }
    }

    fun getAllPokemonDbWithListCompetences(userId: Long, onSuccess: OnSuccess<List<PokemonDB>>) {
        getAll(userId) { listPokemonDb ->
            listPokemonDb?.forEach {
                viewModelScope.launch {
                    competenceRepository.getCompetencesWithPokemonDbId(it.id) { listCompetence ->
                        // To avoid IndexOutOfBoundsException
                        if (listCompetence.isNotEmpty())
                            it.competence1 = listCompetence[0].nom
                        if (listCompetence.size > 1)
                            it.competence2 = listCompetence[1].nom
                        if (listCompetence.size > 2)
                            it.competence3 = listCompetence[2].nom
                    }
                }
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