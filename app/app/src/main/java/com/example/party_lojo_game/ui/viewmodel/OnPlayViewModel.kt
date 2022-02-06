package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.adapter.OnPlayState
import com.example.party_lojo_game.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class OnPlayViewModel @Inject constructor(
    private val localRepository: LocalRepository): ViewModel() {

    var playerIndex: Int = 0
    private val asks = mutableListOf<AsksBO>()
    private val _players = mutableListOf<PlayerBO>()

    private val _state = MutableLiveData<OnPlayState>()
    val state: LiveData<OnPlayState>
        get() = _state

    fun init(type: AskTypeBO, players: PlayersBO) {
        asks.clear()
        _players.addAll(players.players)
        playerIndex = 0
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

    private fun getRandomQuestion(): AsksBO {
        while (true) {
            asks.getOrNull(Random.nextInt(0, asks.size - 1))?.let {
                return it
            }
        }
    }

    private fun getPlayer(): PlayerBO? {
        if (playerIndex >= _players.size || playerIndex <= 0) {
            playerIndex = 0
        }
        val player =  _players.getOrNull(playerIndex)
        playerIndex ++
        return player
    }

    private fun renderTypes(type: AskTypeBO) {
        when (type) {
            AskTypeBO.BEBE_QUIEN, AskTypeBO.YO_NUNCA -> {
                val nextPlayer = getPlayer()
                if (nextPlayer != null) {
                    _state.postValue(
                        OnPlayState.RenderAsk(
                            getRandomQuestion(),
                            nextPlayer,
                        )
                    )
                } else {
                    //TODO render error
                }


            } else -> {
                //TODO verdad o reto
            }
        }
    }
}