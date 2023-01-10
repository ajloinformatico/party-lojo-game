package com.example.party_lojo_game.data.manager

import android.os.Parcelable
import com.example.party_lojo_game.data.AsksBO
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerAndAsksBO(
    val playerBO: PlayerBO,
    val ask: AsksBO
) : Parcelable
