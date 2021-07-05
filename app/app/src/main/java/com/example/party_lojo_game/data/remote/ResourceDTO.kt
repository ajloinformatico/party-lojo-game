package com.example.party_lojo_game.data.remote

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

fun ResourceDTO.toDBO(): Any{
    if (!this.id.isNullOrEmpty() and !this.text.isNullOrEmpty() and !this.type.isNullOrEmpty()){
        when (this.type) {
            "Bebe quien" -> {
                return BebeQuienDBO(
                    id = this.id!!.toLong(),
                    text = this.text!!
                )
            }
            "Yo nunca" -> {
                return YoNuncaDBO(
                    id = this.id!!.toLong(),
                    text = this.text!!
                )
            }
            "Verdad o reto" -> {
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

//TODO MAPPER TO BO
