package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.adapter.OnPlayState
import com.example.party_lojo_game.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnPlayViewModel @Inject constructor(
    private val localRepository: LocalRepository): ViewModel() {

    var index: Int = 0
    val asks = mutableListOf<AsksBO>()

    private val _state = MutableLiveData<OnPlayState>()
    val state: LiveData<OnPlayState>
        get() = _state

    fun init(type: AskTypeBO) {
        asks.clear()
        index = 0
        viewModelScope.launch {
            when (type) {
                AskTypeBO.BEBE_QUIEN -> {
                    asks.addAll(localRepository.selectAllFromBebeQuien)
                    renderTypes(type)
                }
                AskTypeBO.YO_NUNCA -> {
                    asks.addAll(localRepository.selectAllFromYoNunca)
                    renderTypes(type)
                }
                AskTypeBO.VERDAD_O_RETO -> {
                    asks.addAll(localRepository.selectAllFromVerdadOreto)
                    // navigate to error
                }
                else -> {
                    // navigate to error
                }
            }
        }
    }

    private fun renderTypes(type: AskTypeBO) {
        when (type) {
            AskTypeBO.BEBE_QUIEN, AskTypeBO.YO_NUNCA -> {
                asks.getOrNull(index)?.let {
                    _state.postValue(OnPlayState.RenderAsk(it))
                }
            } else -> {
                //TODO
                //no-loop
            }
        }
    }
}