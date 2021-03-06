package com.example.pokecardproject

import android.app.Application
import com.example.pokecardproject.data.database.DatabaseManager

/**
 * New entry point of the application (Referenced in the manifests)
 */
class PokeCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    // Init the database access
    private fun initDatabase() {
        DatabaseManager.getInstance(this)
    }
}