package com.example.party_lojo_game.data.remote

import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.Constants.BEBE_QUIEN_DTO_TYPE
import com.example.party_lojo_game.data.Constants.VERDAD_O_RETO_TYPE
import com.example.party_lojo_game.data.Constants.YO_NUNCA_DTO_TYPE
import com.example.party_lojo_game.data.local.BebeQuienDBO
import com.example.party_lojo_game.data.local.VerdadOretoDBO
import com.example.party_lojo_game.data.local.YoNuncaDBO
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResourceDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("text") val text: String?
): Serializable

//TODO: REFACTOR THIS
fun ResourceDTO.toDBO(): Any{
    if (!this.id.isNullOrEmpty() and !this.text.isNullOrEmpty() and !this.type.isNullOrEmpty()){
        when (this.type) {
            BEBE_QUIEN_DTO_TYPE -> {
                return BebeQuienDBO(
                    id = this.id!!.toLong(),
                    text = this.text!!
                )
            }

            YO_NUNCA_DTO_TYPE -> {
                return YoNuncaDBO(
                    id = this.id!!.toLong(),
                    text = this.text!!
                )
            }

            VERDAD_O_RETO_TYPE -> {
                return VerdadOretoDBO(
                    id = this.id!!.toLong(),
                    text = this.text!!
                )
            }

            else -> {
                return false
            }
        }
    }

    return false
}

fun ResourceDTO.toBO(): Any{
    this.id?.let { id ->
        this.text?.let { text ->
            this.type?.let { type ->
                AsksBO(
                    id = id.toLong(),
                    text = text,
                    type = when (this.type) {
                        BEBE_QUIEN_DTO_TYPE -> AskTypeBO.BEBE_QUIEN
                        YO_NUNCA_DTO_TYPE -> AskTypeBO.YO_NUNCA
                        VERDAD_O_RETO_TYPE -> AskTypeBO.VERDAD_O_RETO
                        else -> AskTypeBO.UNKNONW
                    }
                )
            }
        }
    }
    return false
}

