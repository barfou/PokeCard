package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.Competence
import com.example.pokecardproject.ui.adapter.SelectCompetenceAdapter
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import com.example.pokecardproject.ui.widget.holder.OnCheckedChangeListener
import kotlinx.android.synthetic.main.fragment_add_pokemon3.*

class AddPokemonFragment3 : Fragment(), OnCheckedChangeListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var listCompetence: List<Competence> = emptyList()
    private lateinit var competenceAdapter: SelectCompetenceAdapter

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

        competenceAdapter = SelectCompetenceAdapter(this)

        recycler_view_competence.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = competenceAdapter
        }

        btn_precedent.setOnClickListener {
            findNavController().popBackStack()
        }
        btn_creation.setOnClickListener {
            var listSelected = competenceAdapter.getSelected()
            print(listSelected)
        }

        getListCompetence()
    }

    private fun getListCompetence() {
        mainActivityViewModel.getAllCompetences {
            competenceAdapter.submitList(it)
        }
    }

    override fun invoke() {
    }
}

