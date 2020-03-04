package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "competence")
data class Competence (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nom: String = "",
    @Ignore
    var  is_selected: Boolean = false
)