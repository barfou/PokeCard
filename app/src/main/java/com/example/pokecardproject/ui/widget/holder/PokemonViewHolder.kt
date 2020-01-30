package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonBase
import kotlinx.android.synthetic.main.holder_pokemon.view.*

typealias OnPokemonClickListener = (view: View, pokemon: PokemonBase) -> Unit

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: PokemonBase,
             onPokemonClickListener: OnPokemonClickListener,
             position: Int) {

        itemView.holder_pokemon_textview.text = pokemon.name
        itemView.setOnClickListener{onPokemonClickListener(it, pokemon)}

        // To avoid calling the api for each item (to get image url)
        var pos = position + 1
        Glide.with(itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pos + ".png")
            .into(itemView.holder_pokemon_imgview)
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
