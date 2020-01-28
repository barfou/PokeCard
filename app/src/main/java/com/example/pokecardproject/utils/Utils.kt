package com.example.pokecardproject.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import androidx.core.content.ContextCompat.getSystemService


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