package com.example.pokecardproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.repository.PokeRepository
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: PokeRepository
) : ViewModel() {

    private var _data = mutableListOf<Int>()

    val data: List<Int>
        get() = _data

    fun getListPokemons(onSuccess: OnSuccess<List<PokemonBase>>) {
        viewModelScope.launch {
            repository.getListPokemons()?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(PokeRepository.instance) as T
        }
    }
}