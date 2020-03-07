package com.example.pokecardproject.ui.add_pokemon_activity.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonCompetenceJoin
import com.example.pokecardproject.ui.adapter.OnCheckedChangeListener
import com.example.pokecardproject.ui.adapter.SelectCompetenceAdapter
import com.example.pokecardproject.ui.viewmodel.AddPokemonViewModel
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.fragment_add_pokemon3.*

class AddPokemonFragment3 : Fragment(), OnCheckedChangeListener {

    private lateinit var addPokemonViewModel: AddPokemonViewModel
    private val availableCompetences = 3
    private lateinit var competenceAdapter: SelectCompetenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            addPokemonViewModel = ViewModelProvider(this, AddPokemonViewModel).get()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_pokemon3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        competenceAdapter = SelectCompetenceAdapter(this)

        recycler_view_competence.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = competenceAdapter
        }

        btn_precedent.setOnClickListener {
            findNavController().popBackStack()
        }
        btn_creation.setOnClickListener {

            var listSelected = competenceAdapter.getSelected()

            // Insertion du pokemon et récupération de l'id généré par Room
            addPokemonViewModel.pokemonToAdd?.let {

                addPokemonViewModel.insertPokemonDb(addPokemonViewModel.pokemonToAdd!!) { pokemonDbId ->
                    // Pour chaque compétence sélectionné, ajout d'un enregistrement dans la table asscociative
                    listSelected.forEach { competence ->
                        addPokemonViewModel.insertPokemonCompetenceJoin(
                            PokemonCompetenceJoin(
                                pokemonDbId,
                                competence.id!!
                            )
                        )
                    }
                }
            }

            // Trigger callback
            this.requireActivity().setResult(Activity.RESULT_OK)
            this.requireActivity().finish()
        }

        getListCompetence()
        initLabel()
    }

    private fun getListCompetence() {
        addPokemonViewModel.getAllCompetences {
            competenceAdapter.submitList(it)
        }
    }

    private fun initLabel() {
        tv_competences_restantes.text = "Nombre de compétences restants : " + availableCompetences
    }

    // implementation of OnCheckedChangeListener
    override fun invoke() {
        var usedCompetences = competenceAdapter.getSelectedCount()
        var restAvailable = availableCompetences - usedCompetences
        tv_competences_restantes.text = "Compétences restantes : " + restAvailable
    }
}

