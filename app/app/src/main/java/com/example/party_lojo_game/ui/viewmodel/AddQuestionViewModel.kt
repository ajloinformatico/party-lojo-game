package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AddNewAskState
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
}
