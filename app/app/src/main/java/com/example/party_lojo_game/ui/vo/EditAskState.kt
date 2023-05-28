package com.example.party_lojo_game.ui.vo

import com.example.party_lojo_game.data.AsksBO

/** View states class for EditAskFragment */
sealed class EditAskState {
    object Loading : EditAskState()
    object Render : EditAskState()
    object Error : EditAskState()
    data class RemoveQuestion(
        val removed: Boolean
    ) : EditAskState()
    data class Edit(
        val AskBO: AsksBO
    ) : EditAskState()
}
