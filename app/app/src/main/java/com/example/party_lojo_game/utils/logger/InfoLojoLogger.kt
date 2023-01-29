package com.example.party_lojo_game.utils.logger

import android.util.Log

private const val TAG_DEFAULT = ":::TAG"

/**
 * Log helper
 */
object InfoLojoLogger {

    /**
     * send custom log
     * @param message (String): message to log
     * @param className (String): log class
     */
    fun log(message: String, className: String? = null) {
        Log.d(
            className?.takeIf { it.isNotEmpty() } ?: TAG_DEFAULT,
            message
        )
    }
}