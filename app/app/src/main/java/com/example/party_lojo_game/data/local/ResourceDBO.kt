package com.example.party_lojo_game.data.local

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

data class BebeQuienDBO(
    val id: Long,
    val text: String
)

data class YoNuncaDBO(
    val id: Long,
    val text: String
)

data class VerdadOretoDBO(
    val id: Long,
    val text: String
)

fun BebeQuienDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.BEBE_QUIEN
    )

fun YoNuncaDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.YO_NUNCA
    )

fun VerdadOretoDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.VERDAD_O_RETO
    )