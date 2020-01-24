package com.example.pokecardproject.data.networking.api

import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.model.Pokemons
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeAPI {


    @GET("pokemon")
    suspend fun loadListPokemons(): Pokemons

    @GET
    suspend fun loadPokemon(@Url url: String?): PokemonInfo
}