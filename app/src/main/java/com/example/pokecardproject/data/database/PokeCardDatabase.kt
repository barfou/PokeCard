package com.example.pokecardproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokecardproject.data.database.dao.CompetenceDao
import com.example.pokecardproject.data.database.dao.PokemonCompetenceJoinDao
import com.example.pokecardproject.data.database.dao.PokemonDBDao
import com.example.pokecardproject.data.database.dao.UserDao
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonCompetenceJoin
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.model.User

@Database(
    entities = [
        User::class,
        Competence::class,
        PokemonDB::class,
        PokemonCompetenceJoin::class
    ],
    version = 1,
    exportSchema = true
)
abstract class PokeCardDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val competenceDao: CompetenceDao
    abstract val pokemonDBDao: PokemonDBDao
    abstract val pokemonCompetenceJoinDBDao: PokemonCompetenceJoinDao
}