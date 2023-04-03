package com.example.party_lojo_game.ui.vo

/** View states class for AddYourOwnImages fragment */
sealed class AddYourOwnImagesState {
    object Loading : AddYourOwnImagesState()
    object Render : AddYourOwnImagesState()
    object Error : AddYourOwnImagesState()
}
