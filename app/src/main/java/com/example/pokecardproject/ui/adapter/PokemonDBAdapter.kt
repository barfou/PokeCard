package com.example.pokecardproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.ui.widget.holder.PokemonDBViewHolder

class DiffCallback3 : DiffUtil.ItemCallback<PokemonDB>() {

    override fun areContentsTheSame(oldItem: PokemonDB, newItem: PokemonDB): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areItemsTheSame(oldItem: PokemonDB, newItem: PokemonDB): Boolean {
        return oldItem == newItem
    }
}

class PokemonDBAdapter(val context: Fragment) :

    ListAdapter<PokemonDB, PokemonDBViewHolder>(
        DiffCallback3()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDBViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonDBViewHolder(
            inflater.inflate(
                R.layout.holder_pokemon_db,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonDBViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}