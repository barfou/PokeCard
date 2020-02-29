package com.example.pokecardproject.ui.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.select_competence_item.view.*

typealias OnCheckedChangeListener = () -> Unit

class SelectCompetenceAdapter(
    private val onCheckedChangeListener: OnCheckedChangeListener
) :
    RecyclerView.Adapter<SelectCompetenceAdapter.SelectCompetenceViewHolder>() {

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

    class SelectCompetenceViewHolder(private val adapter: SelectCompetenceAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(competence: Competence, onCheckedChangeListener: OnCheckedChangeListener) {

            itemView.tv_nom.text = competence.nom
            itemView.checkbox.isChecked = competence.is_selected
            itemView.checkbox.setOnCheckedChangeListener { _, b ->
                itemView.checkbox.isEnabled = true
            }
            itemView.checkbox.setOnClickListener {
                showToast(itemView.context, "click event")
            }
            itemView.checkbox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                    when (event?.action) {
                        MotionEvent.ACTION_DOWN ->
                        {
                            // Allow state change and event firing only when user uncheck an item
                            // or check it with a total of selected item < 3
                            val selectedCount = adapter.getSelectedCount()
                            val willBeChecked = !itemView.checkbox.isChecked

                            if (!willBeChecked || willBeChecked && selectedCount < 3) {
                                competence.is_selected = willBeChecked
                                onCheckedChangeListener()
                            } else {
                            }
                        }
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })
        }

        companion object {
            fun create(adapter: SelectCompetenceAdapter, parent: ViewGroup): SelectCompetenceViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.select_competence_item,
                    parent,
                    false
                )
                return SelectCompetenceViewHolder(adapter, view)
            }
        }
    }
}