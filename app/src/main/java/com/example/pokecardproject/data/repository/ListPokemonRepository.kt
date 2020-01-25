package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.data.networking.HttpClientManager
import com.example.pokecardproject.data.networking.api.PokeAPI
import com.example.pokecardproject.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object StoreData {
    var listPokemon: List<PokemonBase>? = null
}

class PokeRepositoryImpl(
    private val api: PokeAPI
) : PokeRepository {

    override suspend fun getListPokemons(): List<PokemonBase>? {

        return withContext(Dispatchers.IO) {
            try {
                if (StoreData.listPokemon == null) {
                    StoreData.listPokemon = api.loadListPokemons().listePokemon
                }
                return@withContext StoreData.listPokemon
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface PokeRepository {

    suspend fun getListPokemons(): List<PokemonBase>?

    companion object {
        /**
         * Singleton for the interface [PokeRepository]
         */
        val instance: PokeRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            PokeRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}