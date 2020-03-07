package com.example.pokecardproject.ui.main_activity.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.adapter.PokemonDBAdapter
import com.example.pokecardproject.ui.adapter.SelectCompetenceAdapter
import com.example.pokecardproject.ui.add_pokemon_activity.AddPokemonActivity
import com.example.pokecardproject.ui.main_activity.MainActivity
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_mes_creations.*
import kotlinx.android.synthetic.main.fragment_mes_creations.view.*

class MesCreationsFragment : Fragment() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var pokemonDBAdapter: PokemonDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            mainActivityViewModel = ViewModelProvider(this, MainActivityViewModel).get()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mes_creations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonDBAdapter = PokemonDBAdapter(this)

        view.recycler_view.apply {
            adapter = pokemonDBAdapter
        }

        loadAdapter()

        btn_go_to_add_pokemon.setOnClickListener {
            mainActivityViewModel.currentUser?.run {
                val intent = Intent(requireActivity(), AddPokemonActivity::class.java)
                intent.putExtra(MainActivity.ARG_USER_ID_KEY, id)
                startActivityForResult(intent, CREATE_POKEMON_ACTION)
            }
        }
    }

    private fun loadAdapter() {
        // Récupération des pokemon du current user
        mainActivityViewModel.currentUser?.run {
            mainActivityViewModel.getAllPokemonDbWithListCompetences(id) {
                pokemonDBAdapter.submitList(it)
                // Pour que l'item nouvellement créé soit toujours visible
                if (it.isNotEmpty()) {
                    recycler_view.smoothScrollToPosition(0)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_POKEMON_ACTION) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                loadAdapter()
            }
        }
    }

    companion object {
        const val CREATE_POKEMON_ACTION = 1
    }
}
