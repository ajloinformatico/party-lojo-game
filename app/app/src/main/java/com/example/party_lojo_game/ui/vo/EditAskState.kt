package com.example.party_lojo_game.ui.vo

/** View states class for EditAskFragment */
sealed class EditAskState {
    object Loading : EditAskState()
    object Render : EditAskState()
    object Error : EditAskState()
}
