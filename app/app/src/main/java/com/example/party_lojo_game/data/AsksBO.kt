package com.example.party_lojo_game.data

import com.example.party_lojo_game.ui.vo.AskTypeVO
import com.example.party_lojo_game.ui.vo.AsksVO

data class AsksBO(
    val id: Long,
    val text: String,
    val type: AskTypeBO
)
fun AsksBO.toBo(): AsksVO =
    AsksVO(
        id = this.id,
        text = this.text,
        type = when (this.type) {
            AskTypeBO.BEBE_QUIEN -> AskTypeVO.BEBE_QUIEN
            AskTypeBO.VERDAD_O_RETO -> AskTypeVO.VERDAD_O_RETO
            AskTypeBO.YO_NUNCA -> AskTypeVO.YO_NUNCA
            AskTypeBO.UNKNONW -> AskTypeVO.UNKNONW
        }
    )
