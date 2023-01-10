package com.example.party_lojo_game.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsksBO(
    val id: Long,
    val text: String,
    val type: AskTypeBO
) : Parcelable
