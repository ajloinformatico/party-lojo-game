package com.example.party_lojo_game.data

import com.example.party_lojo_game.utils.Constants

enum class AskTypeBO(type: String) {
    BEBE_QUIEN(Constants.BEBE_QUIEN_DTO_TYPE),
    YO_NUNCA(Constants.YO_NUNCA_DTO_TYPE),
    VERDAD_O_RETO(Constants.VERDAD_O_RETO_TYPE),
    UNKNONW("")
}