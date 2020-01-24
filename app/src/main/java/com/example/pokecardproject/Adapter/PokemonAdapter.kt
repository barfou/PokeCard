package com.example.pokecardproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokecardproject.Listener.ItemClickListener
import com.example.pokecardproject.Entity.PokemonBase
import com.example.pokecardproject.R
import kotlinx.android.synthetic.main.my_adapter_row.view.*

class DiffCallback : DiffUtil.ItemCallback<PokemonBase>() {

    override fun areItemsTheSame(oldItem: PokemonBase, newItem: PokemonBase): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonBase, newItem: PokemonBase): Boolean {
        return oldItem?.name == newItem?.name
    }
}

class PokemonAdapter(val context: ItemClickListener, private val list: MutableList<PokemonBase>) :
    ListAdapter<PokemonBase, MyViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(
            inflater.inflate(
                R.layout.my_adapter_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(context, getItem(position), position)
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: ItemClickListener, pokemon: PokemonBase, position: Int) {
        itemView.tv1.text = pokemon.name
        // ImageView
        var imageEnd = position + 1
        //var mImgView: ImageView? = null
        Glide.with(itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + imageEnd + ".png")
            .into(itemView.imgView)

        itemView.setOnClickListener {
            context.onItemClicked(url = pokemon.url.toString())
        }
    }
}
