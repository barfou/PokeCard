package com.example.pokecardproject.data.repository

import com.example.pokecardproject.BuildConfig
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.networking.BaseUrlHolder
import com.example.pokecardproject.data.networking.HttpClientManager
import com.example.pokecardproject.data.networking.api.PokeAPI
import com.example.pokecardproject.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailPokemonRepositoryImpl(
    private val api: PokeAPI
) : DetailPokemonRepository {

    override suspend fun getDetailsPokemon(pokemonBase: PokemonBase): PokemonInfo? {

        return withContext(Dispatchers.IO) {
            try {
                if (BaseUrlHolder.baseUrl == BuildConfig.BASE_URL_SRV_LOCAL) {
                    return@withContext api.loadPokemon(pokemonBase.name)
                } else {
                    return@withContext api.loadPokemonDirect(pokemonBase.url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface DetailPokemonRepository {

    suspend fun getDetailsPokemon(pokemonBase: PokemonBase) : PokemonInfo?

    companion object {
        val instance: DetailPokemonRepository by lazy {
            DetailPokemonRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}