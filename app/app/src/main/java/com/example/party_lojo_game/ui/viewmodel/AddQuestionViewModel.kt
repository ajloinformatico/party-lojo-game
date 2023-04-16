package com.example.party_lojo_game.ui.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.mappers.toAskTypeBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AddNewAsKErrorsType
import com.example.party_lojo_game.ui.vo.AddNewAskState
import com.example.party_lojo_game.utils.className
import com.example.party_lojo_game.utils.extensions.checkAskContent
import com.example.party_lojo_game.utils.extensions.checkInputs
import com.example.party_lojo_game.utils.extensions.checkType
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableLiveData<AddNewAskState>()
    val state: LiveData<AddNewAskState>
        get() = _state

    fun init() {
        viewModelScope.launch {
            _state.value = AddNewAskState.Loading
            _state.value = AddNewAskState.Render
        }
    }

    fun saveBtnClick(
        content: String,
        type: String,
        resources: Resources
    ) = viewModelScope.launch {
        val askBO = AsksBO(
            id = 0L,
            text = content,
            type = type.toAskTypeBO(resources)
        )
        // Load state
        if (!askBO.checkInputs()) {
            _state.value = addErrorState(askBO)
        } else {
            saveAsk(askBO)
        }
    }

    private fun saveAsk(askBO: AsksBO) = viewModelScope.launch {
        val ownerId = when (askBO.type) {
            AskTypeBO.VERDAD_O_RETO -> localRepository.insertVerdadOretoAsk(askBO)
            AskTypeBO.YO_NUNCA -> localRepository.insertYoNuncaAsk(askBO)
            AskTypeBO.BEBE_QUIEN -> localRepository.insertBebeQuienAsk(askBO)
            AskTypeBO.UNKNOWN -> {
                null
            }
        }
        ownerId?.let {
            InfoLojoLogger.log("Added ask with id $ownerId", className())
            _state.postValue(AddNewAskState.AddedToDatabase())
        } ?: run {
            InfoLojoLogger.log("Something was wrong", className())
            _state.postValue(
                AddNewAskState.Error(
                    AddNewAsKErrorsType.CONTENT_ALREADY_SAVED.apply {
                        value = askBO.text
                    }
                )
            )
        }
    }

    private fun addErrorState(askBO: AsksBO): AddNewAskState {
        askBO.apply {
            val contentError: Boolean = !text.checkAskContent()
            val typeError: Boolean = !type.checkType()
            return AddNewAskState.Error(
                when {
                    contentError && !typeError -> AddNewAsKErrorsType.CONTENT_ERROR
                    !contentError && typeError -> AddNewAsKErrorsType.TYPE_ERROR
                    !contentError && !typeError -> AddNewAsKErrorsType.CONTENT_ERROR_AND_TYPE_ERROR
                    else -> AddNewAsKErrorsType.UNKNOWN
                }
            )
        }
    }
}
