package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.utils.removeDrawable
import com.example.pokecardproject.utils.setDrawableRight
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.fragment_add_pokemon2.*

class AddPokemonFragment2 : Fragment() {

    private val initialAvailablePoints = 250
    private var restAvailable = 250

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
        initListeners()
        initLabel()
    }

    private fun setButtonsClickListeners() {
        btn_suivant.setOnClickListener {
            if (restAvailable < 0) {
                showToast(this.requireContext(), getString(R.string.plus_de_points))
            } else {
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

