package com.example.party_lojo_game.ui.vo

/** View states class for AddNewAskFragment */
sealed class AddNewAskState {
    object Loading : AddNewAskState()
    object Render : AddNewAskState()
    object Error : AddNewAskState()
}
