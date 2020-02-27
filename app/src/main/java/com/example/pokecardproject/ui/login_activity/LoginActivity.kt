package com.example.pokecardproject.ui.login_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.pokecardproject.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_activity_fab.setOnClickListener {
            findNavController(R.id.login_fragment_container).navigate(R.id.go_to_params)
        }
    }

    override fun onNavigateUp(): Boolean {
        // We just say to the activity that its back stack will manage by the NavController
        return findNavController(R.id.login_fragment_container).navigateUp()
    }
}
