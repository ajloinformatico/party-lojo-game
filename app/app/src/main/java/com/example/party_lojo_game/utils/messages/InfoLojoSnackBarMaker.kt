package com.example.party_lojo_game.utils.messages

import android.content.Context
import android.view.View
import com.example.party_lojo_game.R
import com.google.android.material.snackbar.Snackbar

object InfoLojoSnackBarMaker {

    fun showError(context: Context, view: View, text: String) =
        create(
            view = view,
            text = text,
            timeDuration = Snackbar.LENGTH_LONG,
            context = context,
            backGroundColor = R.color.red,
            textColor = R.color.white
        )

    fun showSuccess(context: Context, view: View, text: String) {
        create(
            view = view,
            text = text,
            timeDuration = Snackbar.LENGTH_SHORT,
            context = context,
            backGroundColor = R.color.green,
            textColor = R.color.black
        )
    }

    fun showSimple(
        context: Context,
        view: View,
        text: String,
        timeDuration: Int = Snackbar.LENGTH_SHORT
    ) {
        create(
            view = view,
            text = text,
            timeDuration = timeDuration,
            context = context,
            backGroundColor = R.color.green,
            textColor = R.color.black
        )
    }

    /** Init a SnackBar with textColor and backGroundColor*/
    private fun create(
        view: View,
        text: String,
        timeDuration: Int = Snackbar.LENGTH_SHORT,
        context: Context? = null,
        textColor: Int? = null,
        backGroundColor: Int? = null
    ) {
        Snackbar.make(view, text, timeDuration).apply {
            context?.let { ctx ->
                textColor?.let {
                    setTextColor(ctx.getColor(it))
                }
                backGroundColor?.let {
                    setBackgroundTint(ctx.getColor(it))
                }
            }
            show()
        }
    }
}
