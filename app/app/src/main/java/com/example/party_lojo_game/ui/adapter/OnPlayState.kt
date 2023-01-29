package com.example.party_lojo_game.ui.adapter

import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.manager.PlayerBO

sealed class OnPlayState {
    data class RenderAsk(
        val asksBO: AsksBO?,
        val player: PlayerBO
    ) : OnPlayState()

    object Error : OnPlayState()
    object Loading : OnPlayState()
}
