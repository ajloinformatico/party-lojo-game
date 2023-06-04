package com.example.party_lojo_game.ui.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.data.local.dbo.toBO
import com.example.party_lojo_game.data.mappers.toVo
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AsksVO
import com.example.party_lojo_game.ui.vo.EditAskState
import com.example.party_lojo_game.ui.vo.toBO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val YO_NUNCA = "Yo Nunca"
private const val BEBE_QUIEN = "Bebe Quien"

@HiltViewModel
class EditQuestionViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableLiveData<EditAskState>()
    val state: LiveData<EditAskState>
        get() = _state

    // Questions to load in recyclerView
    private val _questions = MutableLiveData<List<AsksVO>>()
    val questions: LiveData<List<AsksVO>>
        get() = _questions

    // Questions to manage
    private val questionsBO = mutableListOf<AsksBO>()

    fun init() {
        clearAllAsks()
        viewModelScope.launch {
            _state.value = EditAskState.Loading
        }
    }

    private fun clearAllAsks() {
        _questions.value = emptyList()
        questionsBO.clear()
    }

    fun getAllYoNuncaAsks(): LiveData<List<YoNuncaDBO>> =
        localRepository.selectAllFromYoNunca.asLiveData()

    fun getAllBebeQuienAsks(): LiveData<List<BebeQuienDBO>> =
        localRepository.selectAllFromBebeQuien.asLiveData()

    fun updateYoNuncaBOAsks(yoNuncaDBO: List<YoNuncaDBO>) {
        questionsBO.addAll(yoNuncaDBO.map { it.toBO() })
    }

    fun updateBebeQuienBOAsks(bebeQuienDBO: List<BebeQuienDBO>) {
        questionsBO.addAll(bebeQuienDBO.map { it.toBO() })
    }

    /** Remove ask from grid */
    private fun removeFromGrid(id: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            questionsBO.removeIf { it.id == id }
        } else {
            questionsBO.forEachIndexed { index, ask ->
                if (ask.id == id) {
                    questionsBO.removeAt(index)
                }
            }
        }
        load(render = false)
    }

    /** load asks */
    fun load(render: Boolean) {
        var yoNuncaTitle: Boolean = false
        var bebeQuienTitle: Boolean = false
        val asksVO = mutableListOf<AsksVO>()
        questionsBO.forEach {
            // Note: Add titles
            if (!yoNuncaTitle && it.type == AskTypeBO.YO_NUNCA) {
                asksVO.add(AsksVO.TitleAskVO(-1, YO_NUNCA))
                yoNuncaTitle = true
            }
            if (yoNuncaTitle && !bebeQuienTitle && it.type == AskTypeBO.BEBE_QUIEN) {
                asksVO.add(AsksVO.TitleAskVO(-1, BEBE_QUIEN))
                bebeQuienTitle = true
            }
            // Note: Ass normal questions
            asksVO.add(it.toVo())
        }
        _questions.value = asksVO.toList()
        if (render) _state.value = EditAskState.Render
    }

    fun remove(questionId: Long, askTypeBO: AskTypeBO) {
        viewModelScope.launch {
            val removed = localRepository.removeAsk(
                id = questionId,
                type = askTypeBO
            )
            if (removed) {
                removeFromGrid(questionId)
            }
            _state.value = EditAskState.RemoveQuestion(
                removed = removed
            )
        }
    }

    fun editQuestion(ask: AsksVO.AskVO) {
        viewModelScope.launch {
            _state.value = EditAskState.Edit(
                AskBO = ask.toBO()
            )
        }
    }
}
