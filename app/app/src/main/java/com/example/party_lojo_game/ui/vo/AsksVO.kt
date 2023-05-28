package com.example.party_lojo_game.ui.vo

sealed class AsksVO(
    open val id: Long,
    open val type: AskTypeVO
) {
    data class TitleAskVO(
        override val id: Long,
        val title: String,
        override val type: AskTypeVO = AskTypeVO.TITLE
    ) : AsksVO(id, type)

    data class AskVO(
        override val id: Long,
        val text: String,
        override val type: AskTypeVO
    ) : AsksVO(id, type)
}
