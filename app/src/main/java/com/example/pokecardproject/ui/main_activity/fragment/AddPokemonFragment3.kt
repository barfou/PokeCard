package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Ability
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_add_pokemon3.*
import kotlinx.android.synthetic.main.fragment_choice_login.*

class AddPokemonFragment3 : Fragment() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var listCompetence: List<Competence>

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
        return inflater.inflate(R.layout.fragment_add_pokemon3, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_precedent.setOnClickListener {
            findNavController().popBackStack()
        }

        getListCompetence()
        buildUIDynamically()
    }

    private fun getListCompetence() {
        mainActivityViewModel.getAllCompetences {
            listCompetence = it
        }
    }

    private fun buildUIDynamically() {
        if (listCompetence.isNotEmpty()) {

        }
    }
}

