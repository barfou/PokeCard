package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonDb")
data class PokemonDB(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var nom: String = "",
    var taille: Int = 0,
    var poids: Int = 0,
    var attaque: Int = 0,
    var defense: Int = 0,
    var attaqueSpeciale: Int = 0,
    var defenseSpeciale: Int = 0,
    @Ignore
    var competences: List<Competence> = emptyList()
)