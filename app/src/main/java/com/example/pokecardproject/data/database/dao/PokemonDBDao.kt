package com.example.pokecardproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.model.User

@Dao
interface PokemonDBDao {

    @Query("SELECT * FROM pokemonDb")
    fun getAll(): List<PokemonDB>

    @Query("SELECT * FROM pokemonDb WHERE userId=:userId ORDER BY id DESC")
    fun getAllPokemonsOfUser(userId: Long): List<PokemonDB>

    @Insert
    fun insert(pokemonDB: PokemonDB): Long

    @Query("SELECT COUNT(*) from pokemonDb")
    fun getCount(): Int
}