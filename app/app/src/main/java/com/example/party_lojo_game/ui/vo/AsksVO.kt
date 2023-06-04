package com.example.party_lojo_game.ui.vo

import com.example.party_lojo_game.data.AsksBO

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

fun AsksVO.AskVO.toBO(): AsksBO = AsksBO(
    id = this.id,
    text = this.text,
    type = this.type.toBO()
)
