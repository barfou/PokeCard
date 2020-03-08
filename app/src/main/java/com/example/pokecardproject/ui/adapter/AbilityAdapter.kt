package com.example.pokecardproject.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pokecardproject.data.model.Ability
import com.example.pokecardproject.ui.widget.holder.AbilityViewHolder

class DiffCallback2 : DiffUtil.ItemCallback<Ability>() {

    override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
        return oldItem.detail?.name == newItem.detail?.name
    }

    override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
        return oldItem === newItem
    }
}

class AbilityAdapter(val context: Fragment) :

    ListAdapter<Ability, AbilityViewHolder>(
        DiffCallback2()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}