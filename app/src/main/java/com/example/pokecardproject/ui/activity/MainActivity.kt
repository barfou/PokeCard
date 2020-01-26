package com.example.pokecardproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.pokecardproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

//https://swapi.co/api/people/
//http://www.jsonschema2pojo.org/
//https://www.chillcoding.com/blog/2017/03/14/requete-http-get-retrofit-android/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()

        nav_bar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_bar.getMenu().getItem(2).setChecked(true);
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_pokecoins -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_pokecoin)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_pokedex -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_pokedex)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_fight -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_pokefight)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_pokesettings)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onNavigateUp(): Boolean {
        // We just say to the activity that its back stack will manage by the NavController
        return findNavController(R.id.main_fragment_container).navigateUp()
    }

    /**
     * Init the ToolBar
     */
    private fun initToolBar() {
        setSupportActionBar(main_tool_bar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        main_tool_bar.setNavigationOnClickListener { onNavigateUp() }
    }
}
