package com.example.party_lojo_game.ui.adapter

import com.example.party_lojo_game.data.AsksBO

sealed class OnPlayState {
    data class RenderAsk(
        val asksBO: AsksBO,
    ): OnPlayState()
    object Error: OnPlayState()
    //TODO LOADING STATE
}