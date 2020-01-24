package com.example.pokecardproject.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokecardproject.R


private const val ARG_VALUE_SECOND = "param1"

class PokeCoinFragment : Fragment() {

    companion object {

        fun newInstance(valueToDisplay: String) =
            PokeCoinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VALUE_SECOND, valueToDisplay)
                }
            }
    }

    private var valueToDisplay: String? = null

    private var listener: OnPokeCoindFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            valueToDisplay = it.getString(ARG_VALUE_SECOND)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokecoin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*tvSecond.text = valueToDisplay

        bGoToFirst.setOnClickListener {
            onSecondButtonPressed()
        }*/
    }

    /*private fun onSecondButtonPressed() {
        listener?.onPokeCoinFragmentInteraction()
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPokeCoindFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPokeCoindFragmentInteractionListener {
        fun onPokeCoinFragmentInteraction()
    }
}
