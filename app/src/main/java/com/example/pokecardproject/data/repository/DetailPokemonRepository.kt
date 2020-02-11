package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.networking.HttpClientManager
import com.example.pokecardproject.data.networking.api.PokeAPI
import com.example.pokecardproject.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailPokemonRepositoryImpl(
    private val api: PokeAPI
) : DetailPokemonRepository {

    override suspend fun getDetailsPokemon(name: String): PokemonInfo? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext api.loadPokemon(name)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    override suspend fun getDetailsPokemonDirect(url: String): PokemonInfo? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext api.loadPokemonDirect(url)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface DetailPokemonRepository {

    suspend fun getDetailsPokemon(name: String) : PokemonInfo?

    suspend fun getDetailsPokemonDirect(url: String) : PokemonInfo?

    companion object {
        val instance: DetailPokemonRepository by lazy {
            DetailPokemonRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}