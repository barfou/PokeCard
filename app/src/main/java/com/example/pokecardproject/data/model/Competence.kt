package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "competence")
data class Competence (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nom: String,
    @Ignore
    val  is_selected: Boolean
)