package com.example.party_lojo_game.data.mappers

import android.content.res.Resources
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.AskTypeBO

fun String.toAskTypeBO(resources: Resources): AskTypeBO = when (this) {
    resources.getString(R.string.bebe_quien) -> AskTypeBO.BEBE_QUIEN
    resources.getString(R.string.yo_nunca) -> AskTypeBO.YO_NUNCA
    resources.getString(R.string.verdad_o_reto) -> AskTypeBO.VERDAD_O_RETO
    else -> AskTypeBO.UNKNOWN
}
