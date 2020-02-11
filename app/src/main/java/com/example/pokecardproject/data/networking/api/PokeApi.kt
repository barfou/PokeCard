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

    /**
     * CALL POKE-API DIRECT
     */

    @GET(GET_ALL_POKEMON_PATH)
    suspend fun getAllPokemonDirect(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PaginatedResult<PokemonBase>>

    @GET("pokemon/?limit=1000")
    suspend fun loadListPokemonsDirect(): Pokemons

    @GET
    suspend fun loadPokemonDirect(@Url url: String?): PokemonInfo

    /**
     * CALL API PERSO
     */

    @GET(GET_ALL_POKEMON_PATH)
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PaginatedResult<PokemonBase>>

    @GET(GET_POKEMON_DETAIL_PATH)
    suspend fun loadPokemon(
        @Query("name") name: String
    ): PokemonInfo

    companion object {
        //const val GET_ALL_POKEMON_PATH = "pokemon"
        const val GET_ALL_POKEMON_PATH = "pokemons"

        const val GET_POKEMON_DETAIL_PATH = "pokemons/detail"

    }
}