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
        itemView.checkbox_selected.isChecked = competence.is_selected
        itemView.checkbox_selected.setOnCheckedChangeListener { _, b ->
            competence.is_selected = b
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