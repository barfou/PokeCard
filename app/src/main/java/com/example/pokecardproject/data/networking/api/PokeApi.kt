package com.example.pokecardproject.data.networking.api

import com.example.pokecardproject.data.model.PaginatedResult
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.model.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeAPI {

    /*
        // For the paginated list
    @GET(GET_ALL_USER_PATH)
    suspend fun getAllUser(
        @Query("since") id: Int
    ): Response<List<User>>
     */

    // https://pokeapi.co/api/v2/pokemon?offset=20&limit=20
    // For the paginated list
    @GET(GET_ALL_POKEMON_PATH)
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PaginatedResult<PokemonBase>>

    @GET("pokemon/?limit=1000")
    suspend fun loadListPokemons(): Pokemons

    @GET
    suspend fun loadPokemon(@Url url: String?): PokemonInfo

    companion object {
        const val GET_ALL_POKEMON_PATH = "pokemon"
    }
}