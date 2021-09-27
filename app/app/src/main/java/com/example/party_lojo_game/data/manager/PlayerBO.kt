package com.example.party_lojo_game.data.manager
import java.io.Serializable

data class PlayerBO(
    val name: String,
    val resource: String,
    val position: Int
): Serializable