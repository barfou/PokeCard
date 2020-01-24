package com.example.pokecardproject.Listener

import com.example.pokecardproject.Entity.PokemonInfo

interface LoadedListener{
    fun onPokemonInfoLoaded(pokemonInfo: PokemonInfo?)
    fun onListPokemonLoaded()
}

interface ItemClickListener{
    fun onItemClicked(url :String)
}