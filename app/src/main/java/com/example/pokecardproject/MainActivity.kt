package com.example.pokecardproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pokecardproject.Entity.PokemonBase
import com.example.pokecardproject.Fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navbar.navigation
import kotlin.collections.ArrayList

//https://swapi.co/api/people/
//http://www.jsonschema2pojo.org/
//https://www.chillcoding.com/blog/2017/03/14/requete-http-get-retrofit-android/

class MainActivity : AppCompatActivity(), PokeDexFragment.OnPokeDexFragmentInteractionListener, PokeDetailFragment.OnPokeDetailFragmentInteractionListener,
    PokeCoinFragment.OnPokeCoindFragmentInteractionListener, PokeSettingsFragment.OnPokeSettingsFragmentInteractionListener, PokeFightFragment.OnPokeFightFragmentInteractionListener {

    companion object {
        var listPokemons :MutableList<PokemonBase> = ArrayList<PokemonBase>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.getMenu().getItem(2).setChecked(true);

        openPokeDexFrag()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_pokecoins -> {
                openPokeCoinFrag()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_pokedex -> {
                openPokeDexFrag()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fight -> {
                openPokeFightFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                openPokeSettingsFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragcontainer, fragment)
        //fragmentTransaction.addToBackStack(FragState.PokeDex.toString())
        fragmentTransaction.commit()
    }

    private fun openPokeDexFrag() {
        replaceFragment(PokeDexFragment.newInstance("PokeDexFragment"))
    }

    private fun openPokeCoinFrag() {
        replaceFragment(PokeCoinFragment.newInstance("PokeCoinFragment"))
    }

    private fun openPokeFightFragment() {
        replaceFragment(PokeFightFragment.newInstance("PokeFightFragment"))
    }

    private fun openPokeSettingsFragment() {
        replaceFragment(PokeSettingsFragment.newInstance("PokeSettingsFragment"))
    }

    private fun openPokeDetailFragment(url :String) {
        replaceFragment(PokeDetailFragment.newInstance(url))
    }

    override fun onPokeDetailFragmentInteraction() {
        //
    }

    override fun onPokeDexFragmentInteraction(url: String) {
        openPokeDetailFragment(url)
    }

    override fun onPokeCoinFragmentInteraction() {
        //
    }

    override fun onPokeSettingsFragmentInteraction() {
        //
    }

    override fun onPokeFightFragmentInteraction() {
        //
    }
}
