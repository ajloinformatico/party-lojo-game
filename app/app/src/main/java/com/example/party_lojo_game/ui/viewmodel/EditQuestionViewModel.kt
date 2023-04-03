package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.EditAskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditQuestionViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableLiveData<EditAskState>()
    val state: LiveData<EditAskState>
        get() = _state

    fun init() {
        viewModelScope.launch {
            _state.value = EditAskState.Loading
            _state.value = EditAskState.Render
        }
    }
}
