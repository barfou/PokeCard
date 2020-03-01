package com.example.pokecardproject.ui.add_pokemon_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.viewmodel.AddPokemonViewModel
import com.example.pokecardproject.utils.removeDrawable
import com.example.pokecardproject.utils.setDrawableRight
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.fragment_add_pokemon2.*
import kotlinx.android.synthetic.main.fragment_add_pokemon2.seek_bar_attack
import kotlinx.android.synthetic.main.fragment_add_pokemon2.seek_bar_defense
import kotlinx.android.synthetic.main.fragment_add_pokemon2.seek_bar_special_attack
import kotlinx.android.synthetic.main.fragment_add_pokemon2.seek_bar_special_defense
import kotlinx.android.synthetic.main.fragment_pokedetail.*

class AddPokemonFragment2 : Fragment() {

    private lateinit var addPokemonViewModel: AddPokemonViewModel
    private val initialAvailablePoints = 250
    private var restAvailable = 250

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
        return inflater.inflate(R.layout.fragment_add_pokemon2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonsClickListeners()
        initListeners()
        initLabel()
    }

    override fun onResume() {
        super.onResume()

        seek_bar_attack.progress = addPokemonViewModel.pokemonToAdd?.attaque ?: 0
        seek_bar_defense.progress = addPokemonViewModel.pokemonToAdd?.defense ?: 0
        seek_bar_special_attack.progress = addPokemonViewModel.pokemonToAdd?.attaqueSpeciale ?: 0
        seek_bar_special_defense.progress = addPokemonViewModel.pokemonToAdd?.defenseSpeciale ?: 0
    }

    private fun setButtonsClickListeners() {
        btn_suivant.setOnClickListener {
            if (restAvailable < 0) {
                showToast(this.requireContext(), getString(R.string.plus_de_points))
            } else {
                addPokemonViewModel.pokemonToAdd?.attaque = seek_bar_attack.progress
                addPokemonViewModel.pokemonToAdd?.defense = seek_bar_defense.progress
                addPokemonViewModel.pokemonToAdd?.attaqueSpeciale = seek_bar_special_attack.progress
                addPokemonViewModel.pokemonToAdd?.defenseSpeciale = seek_bar_special_defense.progress
                findNavController().navigate(R.id.action_add_pokemon2_fragment_to_add_pokemon_fragment3)
            }
        }

        btn_precedent.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initListeners() {

        setSeekBarListener(seek_bar_attack, tv_count_attaque)
        setSeekBarListener(seek_bar_defense, tv_count_defense)
        setSeekBarListener(seek_bar_special_attack, tv_count_special_attaque)
        setSeekBarListener(seek_bar_special_defense, tv_count_special_defense)
    }

    private fun initLabel() {
        tv_show_total.text = "Nombre de points restants : " + initialAvailablePoints
    }

    private fun setSeekBarListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                textView.text = "$i"
                updateRestAvailable()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun updateRestAvailable() {
        restAvailable = initialAvailablePoints - (seek_bar_attack.progress
                + seek_bar_defense.progress
                + seek_bar_special_attack.progress
                + seek_bar_special_defense.progress)
        tv_show_total.text = "Points restants : " + restAvailable

        if (restAvailable < 0) {
            setDrawableRight(tv_show_total, R.drawable.error)
        } else {
            removeDrawable(tv_show_total)
        }
    }
}

