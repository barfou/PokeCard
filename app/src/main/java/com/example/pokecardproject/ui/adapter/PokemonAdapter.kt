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

class PokemonAdapterSinglePage(
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


