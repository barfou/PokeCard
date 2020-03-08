package com.example.pokecardproject.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.ui.widget.holder.OnCheckedChangeListener
import com.example.pokecardproject.ui.widget.holder.SelectCompetenceViewHolder

class SelectCompetenceAdapter(
    private val onCheckedChangeListener: OnCheckedChangeListener
) :
    RecyclerView.Adapter<SelectCompetenceViewHolder>() {

    var data = emptyList<Competence>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCompetenceViewHolder {
        return SelectCompetenceViewHolder.create(this, parent)
    }

    override fun onBindViewHolder(holder: SelectCompetenceViewHolder, position: Int) {
        holder.bind(data[position], onCheckedChangeListener)
    }

    override fun getItemCount() = data.size

    fun getSelected() = data.filter { it.is_selected }

    fun getSelectedCount() = getSelected().size

    fun submitList(data: List<Competence>) {
        this.data = data
        notifyDataSetChanged()
    }

    companion object : DiffUtil.ItemCallback<PokemonBase>() {
        override fun areItemsTheSame(oldItem: PokemonBase, newItem: PokemonBase) =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: PokemonBase, newItem: PokemonBase) =
            oldItem == newItem
    }
}