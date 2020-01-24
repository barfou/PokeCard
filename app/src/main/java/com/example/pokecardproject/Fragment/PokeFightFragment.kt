package com.example.pokecardproject.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokecardproject.R


private const val ARG_VALUE_SECOND = "param1"

class PokeFightFragment : Fragment() {

    companion object {

        fun newInstance(valueToDisplay: String) =
            PokeFightFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VALUE_SECOND, valueToDisplay)
                }
            }
    }

    private var valueToDisplay: String? = null

    private var listener: OnPokeFightFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            valueToDisplay = it.getString(ARG_VALUE_SECOND)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokefight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*tvSecond.text = valueToDisplay

        bGoToFirst.setOnClickListener {
            onSecondButtonPressed()
        }*/
    }

    /*private fun onSecondButtonPressed() {
        listener?.onPokeFightFragmentInteraction()
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPokeFightFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPokeFightFragmentInteractionListener {
        fun onPokeFightFragmentInteraction()
    }
}