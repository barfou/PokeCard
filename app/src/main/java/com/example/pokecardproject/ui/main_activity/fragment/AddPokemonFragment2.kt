package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import kotlinx.android.synthetic.main.fragment_add_pokemon2.*
import kotlinx.android.synthetic.main.fragment_choice_login.*

class AddPokemonFragment2 : Fragment() {

    val availablePoints = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setSeekBarListener()
        initLabel()
    }

    private fun setButtonsClickListeners() {
        btn_suivant.setOnClickListener {
            findNavController().navigate(R.id.action_add_pokemon2_fragment_to_add_pokemon_fragment3)
        }

        btn_precedent.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setSeekBarListener() {

        // SeekBar Attaque
        seek_bar_attack.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                tv_count_attaque.text = "$i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // SeekBar Defense
        seek_bar_defense.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tv_count_defense.text = "$i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // SeekBar Special Attaque
        seek_bar_special_attack.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tv_count_special_attaque.text = "$i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // SeekBar Special Defense
        seek_bar_special_defense.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tv_count_special_defense.text = "$i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun initLabel() {
        tv_show_total.text = "Nombre de points restants : " + availablePoints
    }
}

