package com.example.pokecardproject.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModel).get()

        setContentView(R.layout.activity_main)

        nav_bar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_bar.getMenu().getItem(2).setChecked(true)

        var userId = intent.getLongExtra(ARG_USER_ID_KEY, -1)
        if (userId > 0) {
            mainActivityViewModel.getUser(userId = userId) {
                mainActivityViewModel.currentUser = it
            }
        }

        mainActivityViewModel.initTableCompetence()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_mes_creations -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_mes_creations)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_pokedex -> {
                    findNavController(R.id.main_fragment_container).navigate(R.id.go_to_pokedex)
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

    companion object {
        const val ARG_USER_ID_KEY = "arg_user_id_key"
    }
}
