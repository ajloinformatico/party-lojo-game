package com.example.party_lojo_game.data.local.dbo

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

data class BebeQuienDBO(
    val id: Long,
    val text: String
)

fun BebeQuienDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.BEBE_QUIEN
    )