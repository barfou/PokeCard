package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_db")
data class PokemonDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var nom: String = "",
    var taille: Int = 0,
    var poids: Int = 0,
    var attaque: Int = 0,
    var defense: Int = 0,
    var attaqueSpeciale: Int = 0,
    var defenseSpeciale: Int = 0
)