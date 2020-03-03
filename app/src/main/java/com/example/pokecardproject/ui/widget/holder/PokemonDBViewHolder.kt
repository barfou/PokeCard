package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokecardproject.BuildConfig
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.data.networking.BaseUrlHolder
import kotlinx.android.synthetic.main.holder_pokemon.view.*
import kotlinx.android.synthetic.main.holder_pokemon_db.view.*

class PokemonDBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemonDB: PokemonDB,
             position: Int) {

        itemView.tv_nom.text = pokemonDB.nom
        itemView.tv_taille.text = pokemonDB.taille.toString()
        itemView.tv_poids.text = pokemonDB.poids.toString()

        itemView.tv_competence_1.text = pokemonDB.competences[0]?.nom
        itemView.tv_competence_2.text = pokemonDB.competences[1]?.nom
        itemView.tv_competence_3.text = pokemonDB.competences[2]?.nom

        itemView.seek_bar_attaque.progress = pokemonDB.attaque
        itemView.seek_bar_defense.progress = pokemonDB.defense
        itemView.seek_bar_speciale_attaque.progress = pokemonDB.attaqueSpeciale
        itemView.seek_bar_speciale_defense.progress = pokemonDB.defenseSpeciale
    }

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_pokemon_db,
                parent,
                false
            )
            return PokemonViewHolder(view)
        }
    }
}
