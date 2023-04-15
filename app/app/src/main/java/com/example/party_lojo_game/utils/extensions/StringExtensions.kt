package com.example.party_lojo_game.utils.extensions

/** Used to check content of asks */
fun String?.checkAskContent(): Boolean = this.orEmpty().isNotBlank()
