package com.example.party_lojo_game.ui.vo

/** View states class for AddNewAskFragment */
sealed class AddNewAskState {
    object Loading : AddNewAskState()
    object Render : AddNewAskState()
    data class AddedToDatabase(
        var message: String = ""
    ) : AddNewAskState()

    data class Error(
        val type: AddNewAsKErrorsType
    ) : AddNewAskState()
}

enum class AddNewAsKErrorsType(var value: String = "") {
    CONTENT_ERROR,
    TYPE_ERROR,
    CONTENT_ERROR_AND_TYPE_ERROR,
    CONTENT_ALREADY_SAVED,
    UNKNOWN
}
