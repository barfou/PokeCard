package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonBase
import kotlinx.android.synthetic.main.select_competence_item.view.*

typealias OnCheckedChangeListener = () -> Unit

class SelectCompetenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(competence: Competence, onCheckedChangeListener: OnCheckedChangeListener) {

        itemView.tv_nom.text = competence.nom
        itemView.checkbox.isChecked = competence.is_selected
        itemView.checkbox.setOnCheckedChangeListener { _, b ->
            competence.is_selected = b
            onCheckedChangeListener()
        }
    }

    companion object {
        fun create(parent: ViewGroup): SelectCompetenceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.select_competence_item,
                parent,
                false
            )
            return SelectCompetenceViewHolder(view)
        }
    }
}