package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonDb",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"]
        )])
data class PokemonDB @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var nom: String,
    var taille: Int,
    var poids: Int,
    var attaque: Int,
    var defense: Int,
    var attaqueSpeciale: Int,
    var defenseSpeciale: Int,
    var userId: Long,
    @Ignore
    var competence1: String = "",
    @Ignore
    var competence2: String = "",
    @Ignore
    var competence3: String = ""
)