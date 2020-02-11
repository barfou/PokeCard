package com.example.pokecardproject.data.model
import com.google.gson.annotations.SerializedName

data class PokemonBase(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlBackImg") val urlBackImg: String,
    @SerializedName("urlFrontImg") val urlFrontImg: String
)