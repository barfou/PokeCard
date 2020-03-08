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
import com.example.pokecardproject.R
import com.example.pokecardproject.data.model.User
import com.example.pokecardproject.ui.main_activity.MainActivity
import com.example.pokecardproject.ui.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment() {

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationViewModel = ViewModelProvider(this, RegistrationViewModel).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edt_login.setOnFocusChangeListener { _: View, hasFocus: Boolean ->
            if (!hasFocus) {
                registrationViewModel.loginExist(edt_login.text.toString()) {
                    if (it) {
                        Toast.makeText(this.context, R.string.utilisateur_existe_deja, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btn_enregistrer.setOnClickListener {
            if (edt_login.text!!.isEmpty() || edt_mail.text!!.isEmpty() || edt_password.text!!.isEmpty()) {
                Toast.makeText(this.context, R.string.saisie_incorrecte, Toast.LENGTH_SHORT).show()
            } else {
                registrationViewModel.loginExist(edt_login.text.toString()) {
                    if (it) {
                        Toast.makeText(this.context, R.string.utilisateur_existe_deja, Toast.LENGTH_SHORT).show()
                        edt_login.requestFocus()
                    }
                    else {
                        // id is set to 0 in object => room handle the autoincrement
                        val newUser = User(0, edt_login.text.toString(), edt_mail.text.toString(), edt_password.text.toString())
                        registrationViewModel.insertUser(user = newUser) { userId ->
                            if (userId > 0) {
                                Toast.makeText(this.context, R.string.utilisateur_bien_enregistre, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this.activity, MainActivity::class.java)
                                intent.putExtra(MainActivity.ARG_USER_ID_KEY, userId)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this.context, R.string.echec_enregistrement, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        requireActivity().login_activity_fab?.visibility = View.VISIBLE
    }
}