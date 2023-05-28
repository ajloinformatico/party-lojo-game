package com.example.party_lojo_game.ui.viewholders

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.RowSingleQuestionBinding
import com.example.party_lojo_game.databinding.RowTitleEditYourQuestionBinding
import com.example.party_lojo_game.databinding.RowUnknownBinding
import com.example.party_lojo_game.ui.adapter.EditYourQuestionsEvents
import com.example.party_lojo_game.ui.vo.AsksVO

sealed class EditYourQuestionsGridViewHolder(
    val view: View
) : RecyclerView.ViewHolder(view) {

    class TitleGroupViewHolder(private val binding: RowTitleEditYourQuestionBinding) :
        EditYourQuestionsGridViewHolder(binding.root) {
        fun bind(title: String) {
            binding.title.text = title
        }
    }

    class SingleQuestionViewHolder(private val binding: RowSingleQuestionBinding) :
        EditYourQuestionsGridViewHolder(binding.root) {
        fun bind(
            ask: AsksVO,
            event: (EditYourQuestionsEvents) -> Unit
        ) {
            (ask as? AsksVO.AskVO)?.let { askData ->
                binding.apply {
                    // Prepare View
                    val context = binding.root.context
                    val resources = binding.root.resources


                    // Paint View
                    titleText.text = askData.text
                    actionButton.setOnClickListener {
                        createEditNoteDialog(
                            editAction = {
                                event(
                                    EditYourQuestionsEvents.Edit(
                                        id = ask.id,
                                        askTypeVO = ask.type
                                    )
                                )
                            },
                            removeAction = {
                                event(
                                    EditYourQuestionsEvents.Remove(
                                        id = ask.id,
                                        askTypeVO = ask.type
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }

        /** Create a dialog, NOTE use it to create the base in the future for my own DialogMaker */
        private fun createEditNoteDialog(
            editAction: () -> Unit,
            removeAction: () -> Unit
        ) {
            val editIcon: Drawable? = ContextCompat.getDrawable(
                view.context,
                R.drawable.ic_edit
            )?.apply { setTint(ContextCompat.getColor(view.context, R.color.white)) }

            val removeIcon: Drawable? = ContextCompat.getDrawable(
                view.context,
                R.drawable.ic_delete_icon
            )

            AlertDialog.Builder(view.context,  R.style.MyDialogTheme).apply {
                setTitle(view.resources.getText(R.string.edit_question_action))
                setIcon(editIcon)
                setPositiveButton(
                    view.resources.getText(R.string.edit)
                ) { _, _ ->
                    editAction.invoke()
                }
                setNegativeButton(
                    view.resources.getText(R.string.remove)
                ) { _, _ ->
                    removeAction.invoke()
                }
                show()
            }
        }
    }

    class UnknownViewHolder(private val binding: RowUnknownBinding) :
        EditYourQuestionsGridViewHolder(binding.root)
}
