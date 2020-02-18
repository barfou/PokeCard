package com.example.pokecardproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokecardproject.data.database.dao.CompetenceDao
import com.example.pokecardproject.data.database.dao.UserDao
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.User

@Database(
    entities = [
        User::class,
        Competence::class
    ],
    version = 1,
    exportSchema = true
)
abstract class PokeCardDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val competenceDao: CompetenceDao
}