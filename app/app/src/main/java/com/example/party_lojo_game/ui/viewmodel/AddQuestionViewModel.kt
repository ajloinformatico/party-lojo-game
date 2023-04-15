package com.example.party_lojo_game.ui.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.mappers.toAskTypeBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AddNewAsKErrorsType
import com.example.party_lojo_game.ui.vo.AddNewAskState
import com.example.party_lojo_game.utils.extensions.checkAskContent
import com.example.party_lojo_game.utils.extensions.checkInputs
import com.example.party_lojo_game.utils.extensions.checkType
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
    ) {
        val askBO = AsksBO(
            id = 0L,
            text = content,
            type = type.toAskTypeBO(resources)
        )
        viewModelScope.launch {
            // Note: Error load error state
            if (!askBO.checkInputs()) {
                Log.d("TonyTest", "something was wrong")
                _state.value = addErrorState(askBO)
            } else {
                Log.d("TonyTest", "can add to database")
                // TODO ADD TO DATABASE
                _state.value = AddNewAskState.AddedToDatabase()

            }
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
