package com.example.party_lojo_game.ui.vo

/** View states class for EditQuestionBottomSheet */
sealed interface EditQuestionBottomSheetState {
    object Loading: EditQuestionBottomSheetState
    object Render: EditQuestionBottomSheetState
    object Error: EditQuestionBottomSheetState
    data class Updated(
        val success: Boolean
    ): EditQuestionBottomSheetState
}