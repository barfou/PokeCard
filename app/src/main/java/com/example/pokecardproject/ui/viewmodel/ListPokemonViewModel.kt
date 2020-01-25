package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.repository.PokeRepository
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val repository: PokeRepository
) : ViewModel() {

    fun getListPokemons(onSuccess: OnSuccess<List<PokemonBase>>) {
        viewModelScope.launch {
            repository.getListPokemons()?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListPokemonViewModel(PokeRepository.instance) as T
        }
    }
}