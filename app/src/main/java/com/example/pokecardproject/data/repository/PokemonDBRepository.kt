package com.example.pokecardproject.data.repository

import com.example.pokecardproject.data.database.DatabaseManager
import com.example.pokecardproject.data.database.dao.PokemonDBDao
import com.example.pokecardproject.data.model.PokemonDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDBRepositoryImpl(
    private val dao: PokemonDBDao
) : PokemonDBRepository {

    override suspend fun getAll(): List<PokemonDB>? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getAll()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    override suspend fun insertPokemonDB(pokemonDB: PokemonDB): Long {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.insert(pokemonDB)
            } catch (e: Exception) {
                e.printStackTrace()
                val v = -1
                return@withContext v.toLong()
            }
        }
    }

    override suspend fun getCount(): Int {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getCount()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext -1
            }
        }
    }

    override suspend fun getAllPokemonsOfUser(userId: Long): List<PokemonDB>? {

        return withContext(Dispatchers.IO) {
            try {
                return@withContext dao.getAllPokemonsOfUser(userId)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}

interface PokemonDBRepository {

    suspend fun getAll(): List<PokemonDB>?

    suspend fun getAllPokemonsOfUser(userId: Long): List<PokemonDB>?

    suspend fun insertPokemonDB(pokemonDB: PokemonDB): Long

    suspend fun getCount(): Int

    companion object {

        val instance: PokemonDBRepository by lazy {
            PokemonDBRepositoryImpl(DatabaseManager.getInstance().database.pokemonDBDao)
        }
    }
}