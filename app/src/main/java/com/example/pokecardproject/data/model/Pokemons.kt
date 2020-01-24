package com.example.pokecardproject.data.model

import com.example.pokecardproject.data.model.PokemonBase
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pokemons {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("previous")
    @Expose
    var previous: Any? = null
    @SerializedName("results")
    @Expose
    var listePokemon: List<PokemonBase>? = null

}