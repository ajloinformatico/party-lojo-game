package com.example.party_lojo_game.data.remote

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.constants.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable


private const val DEFAULT_ID = "0"
private const val DEFAULT_TEXT = "default"

data class ResourceDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("text") val text: String?
) : Serializable

fun ResourceDTO.toBO(): AsksBO =
    AsksBO(
        id = (this.id ?: DEFAULT_ID).toLong(),
        text = this.text ?: DEFAULT_TEXT,
        type = when (this.type) {
            Constants.BEBE_QUIEN_DTO_TYPE -> AskTypeBO.BEBE_QUIEN
            Constants.YO_NUNCA_DTO_TYPE -> AskTypeBO.YO_NUNCA
            Constants.VERDAD_O_RETO_TYPE -> AskTypeBO.VERDAD_O_RETO
            else -> AskTypeBO.UNKNOWN
        }
    )
