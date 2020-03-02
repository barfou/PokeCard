package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 *  Entité qui sert de table associative entre PokemonDB et Competence
 */
@Entity(tableName = "pokemonCompetenceJoin",
    primaryKeys = ["pokemonDbId", "competenceId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonDB::class,
            parentColumns = ["id"],
            childColumns = ["pokemonDbId"]
        ),
        ForeignKey(
            entity = Competence::class,
            parentColumns = ["id"],
            childColumns = ["competenceId"]
        )]
)
data class PokemonCompetenceJoin(
    val pokemonDbId: Long,
    val competenceId: Long
)