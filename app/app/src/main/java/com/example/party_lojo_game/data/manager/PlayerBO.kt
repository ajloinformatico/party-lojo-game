package com.example.party_lojo_game.data.manager
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerBO(
    val name: String,
    val resource: String,
    val position: Int
): Parcelable