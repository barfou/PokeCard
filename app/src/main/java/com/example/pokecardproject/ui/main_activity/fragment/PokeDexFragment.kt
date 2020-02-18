package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.ui.adapter.PokemonAdapter
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import com.example.pokecardproject.ui.widget.holder.OnPokemonClickListener
import kotlinx.android.synthetic.main.fragment_pokedex.*

class PokeDexFragment : Fragment(), OnPokemonClickListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            mainActivityViewModel = ViewModelProvider(this, MainActivityViewModel).get()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (this.context != null) {

            pokemonAdapter = PokemonAdapter(this)

            mainActivityViewModel.pokemonsPagedList.observe(this) {
                pokemonAdapter.submitList(it)
            }

            myRecyclerView.apply {
                layoutManager = GridLayoutManager(this.context, 2)
                adapter = pokemonAdapter
            }
        }
    }

    // implementation of OnPokemonClickListener
    override fun invoke(view: View, pokemon: PokemonBase) {

        /*findNavController().navigate(
            R.id.action_pokemon_list_fragment_to_pokemon_details_fragment,
            bundleOf(PokeDetailFragment.ARG_POKEMON_URL_KEY to pokemon.url.toString())
        )*/

        findNavController().navigate(
            R.id.action_pokemon_list_fragment_to_pokemon_details_fragment,
            bundleOf(PokeDetailFragment.ARG_POKEMON_NAME_KEY to pokemon.name)
        )
    }
}