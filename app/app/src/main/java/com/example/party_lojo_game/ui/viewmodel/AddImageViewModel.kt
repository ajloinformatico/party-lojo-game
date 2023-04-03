package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.vo.AddYourOwnImagesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddImageViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableLiveData<AddYourOwnImagesState>()
    val state: LiveData<AddYourOwnImagesState>
        get() = _state

    fun init() {
        viewModelScope.launch {
            _state.value = AddYourOwnImagesState.Loading
            _state.value = AddYourOwnImagesState.Render
        }
    }
}
