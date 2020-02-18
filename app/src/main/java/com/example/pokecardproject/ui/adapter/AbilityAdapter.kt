package com.example.pokecardproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.data.model.Ability
import com.example.pokecardproject.R
import kotlinx.android.synthetic.main.ability_item.view.*

class DiffCallback2 : DiffUtil.ItemCallback<Ability>() {

    override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
        return oldItem?.detail?.name == newItem?.detail?.name
    }

    override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
        return oldItem == newItem
    }
}

class AbilityAdapter(val context: Fragment) :

    ListAdapter<Ability, AbilityViewHolder>(
        DiffCallback2()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AbilityViewHolder(
            inflater.inflate(
                R.layout.ability_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.bind(context, getItem(position), position)
    }
}

class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Fragment, ability: Ability, position: Int) {
        itemView.tv_ability.text = ability.detail?.name.toString()
    }

}