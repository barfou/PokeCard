package com.example.pokecardproject.ui.main_activity.fragment

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

        btn_go_to_add_pokemon.setOnClickListener {
            mainActivityViewModel.currentUser?.run {
                val intent = Intent(requireActivity(), AddPokemonActivity::class.java)
                intent.putExtra(MainActivity.ARG_USER_ID_KEY, this.id)
                startActivity(intent)
            }
        }
    }
}
