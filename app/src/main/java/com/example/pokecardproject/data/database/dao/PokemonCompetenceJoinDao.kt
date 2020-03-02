package com.example.pokecardproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonCompetenceJoin

@Dao
interface PokemonCompetenceJoinDao {

    @Insert
    fun insert(pokemonCompetenceJoin: PokemonCompetenceJoin): Long

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("""
        SELECT * FROM competence WHERE id IN ( 
        SELECT competenceId from pokemonCompetenceJoin INNER JOIN pokemonDb ON
        pokemonCompetenceJoin.pokemonDbId = pokemonDb.id WHERE
        pokemonDbId = :pokemonDbId
        )
        """)
    fun getCompetencesWithPokemonDbId(pokemonDbId: Long): List<Competence>
}