package com.example.party_lojo_game.utils

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.doInvisible() {
    visibility = View.INVISIBLE
}

/**
 * getClassName
 */
fun View.className(): String = this.javaClass.name
