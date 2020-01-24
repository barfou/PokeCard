package com.example.pokecardproject.listener

import com.example.pokecardproject.data.model.PokemonInfo

interface LoadedListener{
    fun onPokemonInfoLoaded(pokemonInfo: PokemonInfo?)
    fun onListPokemonLoaded()
}

interface ItemClickListener{
    fun onItemClicked(url :String)
}