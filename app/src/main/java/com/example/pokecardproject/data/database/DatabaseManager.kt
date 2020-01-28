package com.example.pokecardproject.data.database

import androidx.room.Room
import com.example.pokecardproject.PokeCardApplication


private class DatabaseManagerImpl(
    override val database: PokeCardDatabase
) : DatabaseManager

interface DatabaseManager {

    val database: PokeCardDatabase

    companion object {
        private const val DATABASE_NAME = "poke_card.db"

        @Volatile
        private var databaseManager: DatabaseManager? = null

        var override: DatabaseManager? = null

        fun getInstance(app: PokeCardApplication? = null): DatabaseManager {
            return override ?: databaseManager ?: synchronized(this) {
                DatabaseManagerImpl(
                    Room.databaseBuilder(
                        app ?: throw IllegalStateException("No Application"),
                        PokeCardDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                ).also {
                    databaseManager = it
                }
            }
        }

    }
}