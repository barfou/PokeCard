package com.example.pokecardproject.ui.add_pokemon_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonDB
import com.example.pokecardproject.ui.viewmodel.AddPokemonViewModel
import kotlinx.android.synthetic.main.fragment_add_pokemon.*

class AddPokemonFragment : Fragment() {

    private lateinit var addPokemonViewModel: AddPokemonViewModel

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
        return inflater.inflate(R.layout.fragment_add_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener {
            if (edt_nom.text!!.isNotEmpty() && edt_taille.text!!.isNotEmpty() && edt_poids.text!!.isNotEmpty()) {
                val taille = Integer.parseInt(edt_taille.text.toString())
                val poids = Integer.parseInt(edt_poids.text.toString())
                addPokemonViewModel.pokemonToAdd =
                    PokemonDB(nom = edt_nom.text.toString(), taille = taille, poids = poids)

                findNavController().navigate(R.id.action_add_pokemon_fragment_to_add_pokemon_fragment2)
            }
        }
    }
}

