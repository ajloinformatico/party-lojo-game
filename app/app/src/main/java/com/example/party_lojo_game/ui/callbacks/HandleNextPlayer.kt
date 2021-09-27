package com.example.party_lojo_game.ui.callbacks

import com.example.party_lojo_game.data.manager.PlayersBO

interface HandleNextPlayer {
        fun nextPlayer(playersBO: PlayersBO)
    }