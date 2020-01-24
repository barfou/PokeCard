package com.example.pokecardproject.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonBase {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}