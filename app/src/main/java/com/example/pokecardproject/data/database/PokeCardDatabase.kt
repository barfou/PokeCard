package com.example.pokecardproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokecardproject.data.database.dao.UserDao
import com.example.pokecardproject.data.model.User

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = true
)
abstract class PokeCardDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}