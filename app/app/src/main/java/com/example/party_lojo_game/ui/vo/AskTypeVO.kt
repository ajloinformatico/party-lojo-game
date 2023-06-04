package com.example.party_lojo_game.ui.vo

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.constants.Constants

enum class AskTypeVO(val value: String) {
    TITLE(""),
    BEBE_QUIEN(Constants.BEBE_QUIEN_TITLE),
    YO_NUNCA(Constants.YO_NUNCA_TITLE),
    VERDAD_O_RETO(Constants.VERDAD_O_RETO_TYPE),
    UNKNONW("")
}

fun AskTypeVO.toBO(): AskTypeBO = when (this) {
    AskTypeVO.BEBE_QUIEN -> AskTypeBO.BEBE_QUIEN
    AskTypeVO.YO_NUNCA -> AskTypeBO.YO_NUNCA
    AskTypeVO.VERDAD_O_RETO -> AskTypeBO.VERDAD_O_RETO
    else -> AskTypeBO.UNKNOWN
}
