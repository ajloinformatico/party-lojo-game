package com.example.party_lojo_game.ui.vo

sealed interface EditYourQuestionsEvents {
    data class Edit(
        val ask: AsksVO.AskVO
    ) : EditYourQuestionsEvents

    data class Remove(
        val id: Long,
        val askTypeVO: AskTypeVO
    ) : EditYourQuestionsEvents
}