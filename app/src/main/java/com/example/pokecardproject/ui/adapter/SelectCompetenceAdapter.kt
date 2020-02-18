package com.example.pokecardproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Ability
import com.example.pokecardproject.data.model.Competence
import kotlinx.android.synthetic.main.ability_item.view.*
import kotlinx.android.synthetic.main.select_competence_item.view.*

class DiffCallback3 : DiffUtil.ItemCallback<Competence>() {

    override fun areContentsTheSame(oldItem: Competence, newItem: Competence): Boolean {
        return oldItem?.nom == newItem?.nom
    }

    override fun areItemsTheSame(oldItem: Competence, newItem: Competence): Boolean {
        return oldItem == newItem
    }
}

class SelectCompetenceAdapter(val context: Fragment) : ListAdapter<Competence, CompetenceViewHolder>(
        DiffCallback3()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetenceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CompetenceViewHolder(
            inflater.inflate(
                R.layout.select_competence_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompetenceViewHolder, position: Int) {
        holder.bind(context, getItem(position), position)
    }
}

class CompetenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Fragment, competence: Competence, position: Int) {
        itemView.tv_nom.text = competence?.nom.toString()
        itemView.checkbox_selected.isChecked = competence?.is_selected
    }

}