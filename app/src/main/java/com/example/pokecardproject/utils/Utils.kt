package com.example.pokecardproject.utils

import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

/**
 * Top level functions : Allow access from everywhere without class name prefix
 */
fun lockSeekBar(seekBar: SeekBar) {
    seekBar.setOnTouchListener(object : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            return true
        }
    })
}

fun setDrawableRight(target: TextView, resource: Int) {
    target.setCompoundDrawablesWithIntrinsicBounds(0, 0, resource, 0)
}

fun removeDrawable(target: TextView) {
    target.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}
