package com.example.pokecardproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("mail") val mail: String,
    @SerializedName("password") val password: String
) {

}