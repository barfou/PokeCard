package com.example.pokecardproject.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.ui.widget.holder.SelectCompetenceViewHolder

class SelectCompetenceAdapter(
    
    val context: Fragment,
    val list: ArrayList<Competence>

) :
    RecyclerView.Adapter<SelectCompetenceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCompetenceViewHolder {

        return SelectCompetenceViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SelectCompetenceViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getSelected(): List<Competence> {

        return list.filter { s -> s.is_selected }
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