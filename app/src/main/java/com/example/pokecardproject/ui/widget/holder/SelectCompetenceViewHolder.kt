package com.example.pokecardproject.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.ui.adapter.SelectCompetenceAdapter
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.holder_competence.view.*

typealias OnCheckedChangeListener = () -> Unit

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
                R.layout.holder_competence,
                parent,
                false
            )
            return SelectCompetenceViewHolder(adapter, view)
        }
    }
}
