package com.example.party_lojo_game.data.manager

import com.example.party_lojo_game.data.AsksBO
import java.io.Serializable

data class PlayerAndAsksBO(
    val playerBO: PlayerBO,
    val ask: AsksBO
): Serializable