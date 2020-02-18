package com.example.pokecardproject.ui.login_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import kotlinx.android.synthetic.main.fragment_choice_login.*

// log with sqlite : https://www.youtube.com/watch?v=1WPAXHhG6u0

class ChoiceLoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_new_user.setOnClickListener {
            findNavController().navigate(R.id.action_choice_login_fragment_to_registration_fragment)
        }
        btn_connexion.setOnClickListener {
            findNavController().navigate(R.id.action_choice_login_fragment_to_connexion_fragment)
        }
    }
}