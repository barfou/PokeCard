package com.example.pokecardproject.utils

import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar


object Utils {

    fun lockSeekBar(seekBar: SeekBar) {
        seekBar.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }
}