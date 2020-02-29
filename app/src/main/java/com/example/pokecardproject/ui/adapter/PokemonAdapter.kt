package com.example.pokecardproject.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.ui.widget.holder.OnPokemonClickListener
import com.example.pokecardproject.ui.widget.holder.PokemonViewHolder
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class PokemonAdapter(
    private val onPokemonClickListener: OnPokemonClickListener
) : PagedListAdapter<PokemonBase, PokemonViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this, onPokemonClickListener, position) }
    }

    companion object : DiffUtil.ItemCallback<PokemonBase>() {
        override fun areItemsTheSame(oldItem: PokemonBase, newItem: PokemonBase): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PokemonBase, newItem: PokemonBase): Boolean {
            return oldItem == newItem
        }
    }
}


