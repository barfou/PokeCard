package com.example.pokecardproject.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokecardproject.*
import com.example.pokecardproject.Adapter.PokemonAdapter
import com.example.pokecardproject.Entity.PokemonInfo
import com.example.pokecardproject.Listener.ItemClickListener
import com.example.pokecardproject.Listener.LoadedListener
import com.example.pokecardproject.Webservice.APIService
import kotlinx.android.synthetic.main.fragment_pokedex.*

private const val ARG_VALUE_FIRST = "value"

class PokeDexFragment : Fragment(), LoadedListener,
    ItemClickListener {

    private val apiService: APIService =
        APIService(this)
    private var valueToDisplay: String? = null
    private var listener: OnPokeDexFragmentInteractionListener? = null

    var mAdapter: PokemonAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(valueToDisplay: String) =
            PokeDexFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VALUE_FIRST, valueToDisplay)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            valueToDisplay = it.getString(ARG_VALUE_FIRST)
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

            mAdapter = PokemonAdapter(
                this as ItemClickListener,
                MainActivity.listPokemons
            )

            if (MainActivity.listPokemons.count() == 0) {

                apiService.GetListPokemons()
            } else {
                mAdapter!!.submitList(MainActivity.listPokemons)
            }

            myRecyclerView.apply {
                layoutManager = GridLayoutManager(this.context, 2)
                adapter = mAdapter
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPokeDexFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPokeDexFragmentInteractionListener {
        fun onPokeDexFragmentInteraction(url :String)
    }


    override fun onListPokemonLoaded() {
        mAdapter!!.submitList(MainActivity.listPokemons)
    }

    override fun onItemClicked(url: String) {
        //apiService.GetPokemon(url)
        listener!!.onPokeDexFragmentInteraction(url)
    }

    override fun onPokemonInfoLoaded(pokemonInfo: PokemonInfo?) {
        // Not needed
    }


}