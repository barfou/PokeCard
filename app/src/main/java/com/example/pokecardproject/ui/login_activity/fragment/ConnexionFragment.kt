package com.example.pokecardproject.ui.login_activity.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.main_activity.MainActivity
import com.example.pokecardproject.ui.viewmodel.ConnexionViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_connexion.*
import kotlinx.android.synthetic.main.fragment_connexion.edt_login
import kotlinx.android.synthetic.main.fragment_connexion.edt_password

class ConnexionFragment : Fragment() {

    private lateinit var connexionViewModel: ConnexionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connexionViewModel = ViewModelProvider(this, ConnexionViewModel).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connexion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_connexion.setOnClickListener {
            if (edt_login.text!!.isEmpty() || edt_password.text!!.isEmpty()) {
                Toast.makeText(this.context, R.string.saisie_incorrecte, Toast.LENGTH_SHORT).show()
            } else {
                connexionViewModel.credentialsOk(
                    edt_login.text.toString(),
                    edt_password.text.toString()
                ) { user ->
                    if (user != null) {
                        val intent = Intent(this.activity, MainActivity::class.java)
                        intent.putExtra(MainActivity.ARG_USER_ID_KEY, user.id)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this.context, R.string.login_incorrect, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btn_registration.setOnClickListener {
            findNavController().navigate(R.id.action_connexion_fragment_to_registration_fragment)
        }
    }

    override fun onResume() {
        super.onResume()

        requireActivity().login_activity_fab?.visibility = View.VISIBLE
    }
}