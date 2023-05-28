package com.example.party_lojo_game.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.party_lojo_game.databinding.RowSingleQuestionBinding
import com.example.party_lojo_game.databinding.RowTitleEditYourQuestionBinding
import com.example.party_lojo_game.databinding.RowUnknownBinding
import com.example.party_lojo_game.ui.viewholders.EditYourQuestionsGridViewHolder
import com.example.party_lojo_game.ui.vo.AskTypeVO
import com.example.party_lojo_game.ui.vo.AsksVO

class EditYourQuestionsGridAdapter(
    private val event: (EditYourQuestionsEvents) -> Unit
) : ListAdapter<AsksVO, EditYourQuestionsGridViewHolder>(EditYourQuestionsDiffUtils()) {

    private var previousType: Int = -1

    override fun getItem(position: Int): AsksVO? = currentList.getOrNull(position)

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type?.ordinal ?: AskTypeVO.UNKNONW.ordinal
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EditYourQuestionsGridViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            AskTypeVO.YO_NUNCA.ordinal,
            AskTypeVO.VERDAD_O_RETO.ordinal,
            AskTypeVO.BEBE_QUIEN.ordinal -> EditYourQuestionsGridViewHolder.SingleQuestionViewHolder(
                // Update with correct binding
                RowSingleQuestionBinding.inflate(layoutInflater, parent, false)
            )
            AskTypeVO.TITLE.ordinal -> EditYourQuestionsGridViewHolder.TitleGroupViewHolder(
                RowTitleEditYourQuestionBinding.inflate(layoutInflater, parent, false)
            )
            AskTypeVO.UNKNONW.ordinal -> EditYourQuestionsGridViewHolder.UnknownViewHolder(
                RowUnknownBinding.inflate(layoutInflater, parent, false)
            )
            else -> {
                EditYourQuestionsGridViewHolder.UnknownViewHolder(
                RowUnknownBinding.inflate(layoutInflater, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: EditYourQuestionsGridViewHolder, position: Int) {
        when (holder) {
            is EditYourQuestionsGridViewHolder.UnknownViewHolder -> { /*ignore*/ }
            is EditYourQuestionsGridViewHolder.SingleQuestionViewHolder -> {
                (getItem(position) as? AsksVO)?.let {
                    holder.bind(
                        ask = it,
                        event = event
                    )
                }
            }
            is EditYourQuestionsGridViewHolder.TitleGroupViewHolder -> {
                (getItem(position) as? AsksVO.TitleAskVO)?.title?.let(holder::bind)
            }
        }
    }

    class EditYourQuestionsDiffUtils : DiffUtil.ItemCallback<AsksVO>() {
        override fun areItemsTheSame(oldItem: AsksVO, newItem: AsksVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AsksVO, newItem: AsksVO): Boolean =
            oldItem == newItem
    }
}

sealed interface EditYourQuestionsEvents {
    data class Edit(
        val id: Long,
        val askTypeVO: AskTypeVO
    ) : EditYourQuestionsEvents

    data class Remove(
        val id: Long,
        val askTypeVO: AskTypeVO
    ) : EditYourQuestionsEvents
}
