package com.example.party_lojo_game.utils

import java.util.*

/**Random number from (inclusive) to (inclusive)*/
fun rand(from: Int, to: Int): Int {
    return Random().nextInt(to + 1 - from) + from
}

fun createImageList(): List<String> {
    val list: MutableList<String> = mutableListOf()
    for (i in 1..47) {
        list.add("user$i.png")
    }
    return list
}
