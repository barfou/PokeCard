package com.example.pokecardproject.ui.adapter

import android.view.LayoutInflater
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

    class SelectCompetenceViewHolder(private val adapter: SelectCompetenceAdapter, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(competence: Competence, onCheckedChangeListener: OnCheckedChangeListener) {

            itemView.tv_nom.text = competence.nom
            itemView.checkbox.isChecked = competence.is_selected

            /** Gestion du click event sur le checkbox :
             *  Autorise le check seulement quand l'utilisateur
             *  n'a pas atteint le quota de compétences autorisées
             */
            itemView.checkbox.setOnClickListener {

                val selectedCount = adapter.getSelectedCount()
                val isChecked = itemView.checkbox.isChecked

                if (!isChecked || isChecked && selectedCount < 3) {
                    competence.is_selected = isChecked
                    onCheckedChangeListener()
                } else {
                    itemView.checkbox.isChecked = false
                    showToast(itemView.context, itemView.context.getString(R.string.plus_de_competences))
                }
            }
        }

        companion object {
            fun create(
                adapter: SelectCompetenceAdapter,
                parent: ViewGroup
            ): SelectCompetenceViewHolder {
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