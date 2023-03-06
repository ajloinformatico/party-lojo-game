package com.example.party_lojo_game.utils.extensions

import androidx.fragment.app.Fragment

/** return Fragment className, used in Info logger helper*/
fun Fragment.className(): String = this.javaClass.name
