package com.example.pokecardproject.ui.main_activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pokecardproject.R
import com.example.pokecardproject.ui.viewmodel.MainActivityViewModel
import com.example.pokecardproject.utils.showToast
import kotlinx.android.synthetic.main.fragment_pokesettings.*

class PokeSettingsFragment : Fragment() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

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
        return inflater.inflate(R.layout.fragment_pokesettings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCurrentUser()

        btn_save_user_change.setOnClickListener {
            mainActivityViewModel.currentUser?.run {
                if (edt_login.text!!.isNotEmpty() && edt_mail.text!!.isNotEmpty() && edt_password.text!!.isNotEmpty()) {
                    mainActivityViewModel.updateUser(
                        edt_login.text.toString(),
                        edt_mail.text.toString(),
                        edt_password.text.toString(),
                        mainActivityViewModel.currentUser!!.id
                    )
                } else {
                    showToast(requireContext(), getString(R.string.saisie_incorrecte))
                }
            }
        }
    }

    private fun loadCurrentUser() {

        mainActivityViewModel.currentUser?.run {
            edt_login.setText(this.login)
            edt_mail.setText(this.mail)
            edt_password.setText(this.password)
        }
    }
}