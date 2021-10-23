package com.example.party_lojo_game.data

import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.ui.vo.AskTypeVO
import com.example.party_lojo_game.ui.vo.AsksVO
import java.io.Serializable

data class AsksBO(
    val id: Long,
    val text: String,
    val type: AskTypeBO
): Serializable

fun AsksBO.toVo(): AsksVO =
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

fun AsksBO.toDBO(): Any? {
    return when (this.type) {
        AskTypeBO.BEBE_QUIEN -> {
            BebeQuienDBO(
                id = this.id,
                text = this.text
            )
        }
        AskTypeBO.VERDAD_O_RETO -> {
            VerdadOretoDBO(
                id = this.id,
                text = this.text
            )
        }
        AskTypeBO.YO_NUNCA -> {
            YoNuncaDBO(
                id = this.id,
                text = this.text
            )
        }
        AskTypeBO.UNKNONW -> {
            null
        }
    }
}