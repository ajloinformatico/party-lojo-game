package com.example.party_lojo_game.utils

import android.app.Activity
import androidx.fragment.app.FragmentManager
import java.util.*

/**Random number from (inclusive) to (inclusive)*/
fun rand(from: Int, to: Int) : Int {
    return Random().nextInt(to+1 - from) + from
}