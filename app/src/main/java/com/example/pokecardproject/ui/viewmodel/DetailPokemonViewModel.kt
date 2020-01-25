package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.repository.DetailPokemonRepository
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: DetailPokemonRepository
) : ViewModel() {

    fun getPokemonDetails(url: String, onSuccess: OnSuccess<PokemonInfo>) {
        viewModelScope.launch {
            repository.getDetailsPokemon(url)?.run(onSuccess)
        }
    }

    companion object Factory: ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return  DetailPokemonViewModel(DetailPokemonRepository.instance) as T
        }
    }
}