package com.example.party_lojo_game.ui.vo

import com.example.party_lojo_game.data.AsksBO

private const val CONTENT_ERROR_VALUE = "Error de contenido"
private const val TYPE_ERROR_VALUE = "El tipo no es valido"
private const val CONTENT_ERROR_TYPE_ERROR_VALUE = "El error y el tipo no es valido"
private const val ADDED_TO_DATABASE_VALUE = "La pregunta se ha guardado correctamente"

/** View states class for AddNewAskFragment */
sealed class AddNewAskState {
    object Loading : AddNewAskState()
    object Render : AddNewAskState()
    data class AddedToDatabase(
        val message: String = ADDED_TO_DATABASE_VALUE
    ) : AddNewAskState()
    data class Error(
        val type: AddNewAsKErrorsType
    ) : AddNewAskState()
}

enum class AddNewAsKErrorsType(val value: String) {
    CONTENT_ERROR(value = CONTENT_ERROR_VALUE),
    TYPE_ERROR(value = TYPE_ERROR_VALUE),
    CONTENT_ERROR_AND_TYPE_ERROR(value = CONTENT_ERROR_TYPE_ERROR_VALUE),
    UNKNOWN(value = "")
}
