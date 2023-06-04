package com.example.party_lojo_game.ui.fragments.editquestion

import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.data.mappers.toDBO
import com.example.party_lojo_game.data.mappers.toVo
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AsksVO
import com.example.party_lojo_game.ui.vo.EditQuestionBottomSheetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditQuestionBottomSheetViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableLiveData<EditQuestionBottomSheetState>()
    val state: LiveData<EditQuestionBottomSheetState>
        get() = _state

    private val _question = MutableLiveData<AsksVO.AskVO>()
    val question: LiveData<AsksVO.AskVO>
        get() = _question

    private var originalAsk: AsksBO? = null

    fun init(ask: AsksBO?) {
        originalAsk = ask
        viewModelScope.launch {
            _state.value = ask?.let {
                _question.value = it.toVo()
                EditQuestionBottomSheetState.Render
            } ?: EditQuestionBottomSheetState.Error
        }
    }

    // TODO VERDAD O RETO IN THE FUTURE
    fun update(text: String) {
        viewModelScope.launch {
            originalAsk?.let { askBO ->
                localRepository.removeAsk(askBO.id, askBO.type)
                val insert: Long? = when (askBO.type) {
                    AskTypeBO.YO_NUNCA  -> localRepository.insertYoNuncaAsk(askBO)
                    AskTypeBO.BEBE_QUIEN  -> localRepository.insertYoNuncaAsk(askBO)
                    AskTypeBO.VERDAD_O_RETO  -> null
                    AskTypeBO.UNKNOWN -> null
                }
                _state.value = EditQuestionBottomSheetState.Updated(insert != null)
            } ?: run {
                _state.value = EditQuestionBottomSheetState.Error
            }
        }
    }

}