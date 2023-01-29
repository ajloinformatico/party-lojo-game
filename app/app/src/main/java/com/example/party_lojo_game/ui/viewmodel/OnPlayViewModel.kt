package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.*
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.ui.adapter.OnPlayState
import com.example.party_lojo_game.data.constants.Constants
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import com.example.party_lojo_game.utils.className
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class OnPlayViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    var playerIndex: Int = 0
    private val asks = mutableListOf<AsksBO>()
    private val _players = mutableListOf<PlayerBO>()

    private val _state = MutableLiveData<OnPlayState>()
    val state: LiveData<OnPlayState>
        get() = _state

    fun init(players: PlayersBO) {
        viewModelScope.launch {
            _state.value = OnPlayState.Loading
            _players.addAll(players.players)
        }
    }

    /**
     * Get all bebeQuienAsks as liveData
     */
    fun getBebeQuienAsks(): LiveData<List<BebeQuienDBO>> =
        localRepository.selectAllFromBebeQuien.asLiveData()

    /**
     * Get all yoNuncaAsks as liveData
     */
    fun getYoNuncaAsks(): LiveData<List<YoNuncaDBO>> =
        localRepository.selectAllFromYoNunca.asLiveData()

    /**
     * Get all verdadORetoAsks as liveData
     */
    fun getVerdadORetoAsks(): LiveData<List<VerdadOretoDBO>> =
        localRepository.selectAllFromVerdadOreto.asLiveData()

    private fun getRandomQuestion(asksBO: List<AsksBO>? = null): AsksBO? =
        asksBO?.takeIf { it.isNotEmpty() }?.let {
            asksBO.getOrNull(Random.nextInt(0, asksBO.size - 1))
        }


    private fun getPlayer(): PlayerBO? {
        if (playerIndex >= _players.size || playerIndex <= 0) {
            playerIndex = 0
        }
        val player = _players.getOrNull(playerIndex)
        playerIndex++
        return player
    }

    /**
     * Handler nextPlayer ask
     */
    fun manageNextPlayerButtonAction(type: AskTypeBO) {
        renderTypes(type, asks)
    }

    private fun cachedList(asksIn: List<AsksBO>) {
        asks.clear()
        asks.addAll(asksIn)
    }


    /**
     * render next ask
     */
    fun renderTypes(type: AskTypeBO, asksBO: List<AsksBO>) {
        viewModelScope.launch {
            InfoLojoLogger.log("asks in renderTypes $asksBO", className())
            // Note cached list only if playerindex == 0
            if (playerIndex == 0) {
                viewModelScope.launch {
                    cachedList(asksBO)
                }
            }

            when (type) {
                AskTypeBO.BEBE_QUIEN, AskTypeBO.YO_NUNCA -> {
                    _state.value =
                        getPlayer()?.let { playerBO ->
                            getRandomQuestion(asksBO)?.let { askBO ->
                                OnPlayState.RenderAsk(
                                    askBO,
                                    playerBO
                                )
                            } ?: run {
                                // TODO ERROR SCREEN
                                InfoLojoLogger.log(
                                    "SHOW WEEOE",
                                    className()
                                )
                                OnPlayState.Loading
                            }
                        } ?: run {
                            // TODO ERROR SCREEN
                            InfoLojoLogger.log(
                                "SHOW WEEOE",
                                className()
                            )
                            OnPlayState.Loading
                        }
                }
                AskTypeBO.VERDAD_O_RETO -> {
                    //TODO VERDAD O RETO SCREEN
                    _state.value = OnPlayState.Loading
                    InfoLojoLogger.log(
                        "TODO VERDAD O RETO SCREEN",
                        className()
                    )

                }
                AskTypeBO.UNKNOWN -> {
                    // TODO ERRROR SCREEN
                    _state.value = OnPlayState.Loading
                    InfoLojoLogger.log(
                        Constants.ERROR_UNKNOWN_ASK_TYPE,
                        className()
                    )
                }
            }
        }
    }
}
