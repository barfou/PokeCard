package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Ability
import kotlinx.android.synthetic.main.holder_ability.view.*

class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(ability: Ability) {
        itemView.tv_ability.text = ability.detail?.name.toString()
    }

    companion object {
        fun create(parent: ViewGroup): AbilityViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_ability,
                parent,
                false
            )
            return AbilityViewHolder(view)
        }
    }
}