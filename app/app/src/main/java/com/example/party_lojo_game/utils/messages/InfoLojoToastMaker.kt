package com.example.party_lojo_game.utils.messages

import android.content.Context
import android.widget.Toast

/** Infolojo custom toast maker */
object InfoLojoToastMaker {

    /** Show simple toast*/
    fun createSimpleToast(
        context: Context,
        info: String,
        length: InfoLojoToastLength = InfoLojoToastLength.LENGTH_SHORT
    ) {
        Toast.makeText(
            context,
            info,
            length.value
        ).show()
    }
}