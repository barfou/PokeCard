package com.example.pokecardproject.ui.main_activity.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.PokemonInfo
import com.example.pokecardproject.ui.adapter.AbilityAdapter
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import com.example.pokecardproject.utils.lockSeekBar
import kotlinx.android.synthetic.main.fragment_pokedetail.*

class PokeDetailFragment : Fragment() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    var mAdapter: AbilityAdapter? = null

    val args: PokeDetailFragmentArgs by navArgs()

    companion object {
        const val ARG_POKEMON_KEY = "arg_pokemon_key"
    }

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
        return inflater.inflate(R.layout.fragment_pokedetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonBase = args.pokemonBase

        mainActivityViewModel.getPokemonDetails(pokemonBase) {
            if (it != null) {
                showInfos(it)
            } else {
                showAlert()
            }
        }

        mAdapter = AbilityAdapter(this)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Erreur")
        builder.setMessage(R.string.download_failed)
        builder.show()
    }

    private fun showInfos(pokemonInfo: PokemonInfo) {

        Glide.with(image_view.context)
            .load(pokemonInfo.png_Urls?.front_default)
            .into(image_view)

        adjustSeekBars(pokemonInfo)

        tv_nom.text = pokemonInfo.name.toString()
        tv_poids.text = pokemonInfo.weight.toString()
        tv_taille.text = pokemonInfo.height.toString()

        if (this.context != null && pokemonInfo.listeAbilty != null) {
            mAdapter?.submitList(pokemonInfo.listeAbilty!!)
        }
    }

    private fun adjustSeekBars(pokemonInfo: PokemonInfo?) {

        var attaque: Int? = pokemonInfo?.getStat(PokemonInfo.StatType.ATTAQUE)?.base_stat
        attaque?.run {
            seek_bar_attack.setProgress(attaque)
            lbl_attaque.append(" : ")
            lbl_attaque.append(attaque.toString())
        }

        var defense: Int? = pokemonInfo?.getStat(PokemonInfo.StatType.DEFENSE)?.base_stat
        defense?.run {
            seek_bar_defense.setProgress(defense)
            lbl_defense.append(" : ")
            lbl_defense.append(defense.toString())
        }

        var attaqueSpeciale: Int? = pokemonInfo?.getStat(PokemonInfo.StatType.ATTAQUE_SPECIALE)?.base_stat
        attaqueSpeciale?.run {
            seek_bar_special_attack.setProgress(attaqueSpeciale)
            lbl_special_attaque.append(" : ")
            lbl_special_attaque.append(attaqueSpeciale.toString())
        }

        var defenseSpeciale: Int? =
            pokemonInfo?.getStat(PokemonInfo.StatType.DEFENSE_SPECIALE)?.base_stat
        defenseSpeciale?.run {
            seek_bar_special_defense.setProgress(this)
            lbl_special_defense.append(" : ")
            lbl_special_defense.append(this.toString())
        }

        lockSeekBar(seek_bar_attack)
        lockSeekBar(seek_bar_defense)
        lockSeekBar(seek_bar_special_attack)
        lockSeekBar(seek_bar_special_defense)
    }
}