package com.example.pokecardproject.ui.login_activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokecardproject.BuildConfig
import com.example.pokecardproject.R
import com.example.pokecardproject.data.networking.BaseUrlHolder
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_params.*


class ParamsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_params, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolBar()

        requireActivity().login_activity_fab?.visibility = View.GONE

        switch_serveur_local.setOnCheckedChangeListener { _, b ->
            if (b) {
                BaseUrlHolder.baseUrl = BuildConfig.BASE_URL_SRV_LOCAL
            } else {
                BaseUrlHolder.baseUrl = BuildConfig.BASE_URL_API
            }
        }
    }

    private fun initToolBar() {

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}