package com.example.pokecardproject.Utils

import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar

class Utils {

    companion object {
        fun lockSeekBar(seekBar: SeekBar) {
            seekBar.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    return true
                }
            })
        }
    }
}