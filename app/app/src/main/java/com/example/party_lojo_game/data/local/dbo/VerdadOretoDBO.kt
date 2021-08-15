package com.example.party_lojo_game.data.local.dbo

import androidx.room.Entity
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

@Entity
data class VerdadOretoDBO(
    val id: Long,
    val text: String
)

fun VerdadOretoDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.VERDAD_O_RETO
    )