package com.example.party_lojo_game.utils.extensions

import com.example.party_lojo_game.data.AskTypeBO

// TODO ADD VERDAD O RETO IN THE FUTURE
fun AskTypeBO.checkType(): Boolean = when (this) {
    AskTypeBO.BEBE_QUIEN, AskTypeBO.YO_NUNCA -> true
    else -> false
}
