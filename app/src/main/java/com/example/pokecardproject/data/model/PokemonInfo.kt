package com.example.pokecardproject.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
},
"sprites":

{
"back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
"back_female": null,
"back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
"back_shiny_female": null,
"front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
"front_female": null,
"front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
"front_shiny_female": null
},
*/

class PokemonInfo : Serializable {
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("weight")
    @Expose
    var weight: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("abilities")
    @Expose
    var listeAbilty: List<Ability>? = null
    @SerializedName("sprites")
    @Expose
    var png_Urls: ImgUrlInfo? = null
    @SerializedName("stats")
    @Expose
    var stats: List<Stat>? = null

    fun getNotNullUrlCount(): Int {
        var count = 0
        if (png_Urls?.front_default != null)
            count++
        if (png_Urls?.front_shiny != null)
            count++
        if (png_Urls?.back_default != null)
            count++
        if (png_Urls?.back_shiny != null)
            count++
        return count
    }

    enum class StatType { ATTAQUE, DEFENSE, ATTAQUE_SPECIALE, DEFENSE_SPECIALE }

    fun getStat(statType: StatType): Stat? {
        when (statType) {
            StatType.ATTAQUE -> return getStatInfo("attack")
            StatType.DEFENSE -> return getStatInfo("defense")
            StatType.ATTAQUE_SPECIALE -> return getStatInfo("special-attack")
            StatType.DEFENSE_SPECIALE -> return getStatInfo("special-defense")
        }
    }

    private fun getStatInfo(name: String) : Stat? {
        var res : Stat? = null
        stats?.forEach { item -> if (item.stat?.name == name) res = item }
        return res
    }
}

class Stat {

    @SerializedName("base_stat")
    @Expose
    var base_stat: Int? = null
    @SerializedName("effort")
    @Expose
    var effort: Int? = null
    @SerializedName("stat")
    @Expose
    var stat: StatInfo? = null
}

class StatInfo {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}

class Ability {
    @SerializedName("ability")
    @Expose
    var detail: Detail? = null
    @SerializedName("is_hidden")
    @Expose
    var is_hidden: Boolean? = null
    @SerializedName("slot")
    @Expose
    var slot: Int? = null
}

class Detail {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}

class ImgUrlInfo {
    @SerializedName("back_default")
    @Expose
    var back_default: String? = null
    @SerializedName("back_female")
    @Expose
    var back_female: String? = null
    @SerializedName("back_shiny")
    @Expose
    var back_shiny: String? = null
    @SerializedName("back_shiny_female")
    @Expose
    var back_shiny_female: String? = null
    @SerializedName("front_default")
    @Expose
    var front_default: String? = null
    @SerializedName("front_female")
    @Expose
    var front_female: String? = null
    @SerializedName("front_shiny")
    @Expose
    var front_shiny: String? = null
    @SerializedName("front_shiny_female")
    @Expose
    var front_shniy_female: String? = null
}