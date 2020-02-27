package com.example.pokecardproject.utils

import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar

// Top level function : Allow access from everywhere without class name prefix
fun lockSeekBar(seekBar: SeekBar) {
    seekBar.setOnTouchListener(object : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            return true
        }
    })
}