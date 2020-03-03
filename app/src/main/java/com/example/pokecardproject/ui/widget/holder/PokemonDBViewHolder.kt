package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokecardproject.BuildConfig
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.networking.BaseUrlHolder
import kotlinx.android.synthetic.main.holder_pokemon.view.*

class PokemonDBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemonDB: PokemonDB,
             position: Int) {

        itemView.holder_pokemon_textview.text = pokemon.name
        itemView.setOnClickListener{onPokemonClickListener(it, pokemon)}

        if (BaseUrlHolder.baseUrl == BuildConfig.BASE_URL_SRV_LOCAL) {
            Glide.with(itemView.context)
                .load(pokemon.urlFrontImg)
                .into(itemView.holder_pokemon_imgview)
        } else {
            Glide.with(itemView.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pos + ".png")
                .into(itemView.holder_pokemon_imgview)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_pokemon,
                parent,
                false
            )
            return PokemonViewHolder(view)
        }
    }
}
