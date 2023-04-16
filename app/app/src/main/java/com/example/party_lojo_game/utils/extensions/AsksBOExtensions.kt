package com.example.party_lojo_game.utils.extensions

import com.example.party_lojo_game.data.AsksBO

// TODO ADD VERDAD O RETO IN THE FUTURE
fun AsksBO.checkInputs() = text.checkAskContent() && type.checkType()
