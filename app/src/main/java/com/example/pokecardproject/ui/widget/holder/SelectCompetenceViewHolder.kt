package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import kotlinx.android.synthetic.main.select_competence_item.view.*

class SelectCompetenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(competence: Competence) {

        itemView.tv_nom.text = competence.nom
        itemView.checkbox_selected.setOnClickListener {
            competence.is_selected = itemView.checkbox_selected.isSelected
        }
    }

    companion object {
        fun create(parent: ViewGroup): SelectCompetenceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_pokemon,
                parent,
                false
            )
            return SelectCompetenceViewHolder(view)
        }
    }
}