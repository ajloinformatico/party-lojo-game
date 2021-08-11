package com.example.party_lojo_game.data.local.dbo

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

data class YoNuncaDBO(
    val id: Long,
    val text: String
)

fun YoNuncaDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.YO_NUNCA
    )