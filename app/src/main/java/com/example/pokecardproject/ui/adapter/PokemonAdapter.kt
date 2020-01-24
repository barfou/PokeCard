package com.example.pokecardproject.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.ui.widget.holder.OnPokemonClickListener
import com.example.pokecardproject.ui.widget.holder.PokemonViewHolder

class PokemonAdapter(
    private val onPokemonClickListener: OnPokemonClickListener
) : RecyclerView.Adapter<PokemonViewHolder>() {

    private var _data = emptyList<PokemonBase>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun getItemCount(): Int = _data.count()

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(_data[position], position = position, onPokemonClickListener = onPokemonClickListener)
    }

    /**
     * Set new data in the list and refresh it
     */
    fun submitList(data: List<PokemonBase>) {
        _data = data
        notifyDataSetChanged()
    }
}


