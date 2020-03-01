package com.example.pokecardproject.ui.add_pokemon_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.main_activity.MainActivity
import com.example.pokecardproject.ui.viewmodel.AddPokemonViewModel

class AddPokemonActivity : AppCompatActivity() {

    private lateinit var addPokemonViewModel: AddPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pokemon)

        addPokemonViewModel = ViewModelProvider(this, AddPokemonViewModel).get()
        var userId = intent.getLongExtra(MainActivity.ARG_USER_ID_KEY, -1)

        if (userId > 0) {
            addPokemonViewModel.getUser(userId = userId) {
                addPokemonViewModel.currentUser = it
            }
        }
    }
}
