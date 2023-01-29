package com.example.party_lojo_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dbo.toBO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.data.repository.RemoteRepository
import com.example.party_lojo_game.data.constants.Constants
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import com.example.party_lojo_game.utils.className
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SUCCESS_REQUEST = "200"
private const val INVALID_REQUEST = "ERROR: Response Failure"
private const val MAKE_SEEDERS = "make seeders"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository
) : ViewModel() {

    /** Get all api service data*/
    fun getRemoteResponse() {
        viewModelScope.launch {
            val yoNuncaAsks: List<AsksBO> =
                remoteRepository.getAllAsksResponse(Constants.YO_NUNCA_URL)
            val bebeQuienAsks: List<AsksBO> =
                remoteRepository.getAllAsksResponse(Constants.BEBE_QUIEN_URL)
            val verdadOretoAsks: List<AsksBO> =
                remoteRepository.getAllAsksResponse(Constants.VERDAD_O_RETO_URL)
            if (yoNuncaAsks.isNotEmpty() && bebeQuienAsks.isNotEmpty() && verdadOretoAsks.isNotEmpty()) {
                InfoLojoLogger.log("$SUCCESS_REQUEST $yoNuncaAsks", className())
                InfoLojoLogger.log("$SUCCESS_REQUEST $bebeQuienAsks", className())
                InfoLojoLogger.log("$SUCCESS_REQUEST $verdadOretoAsks", className())
                seeder(yoNuncaAsks, bebeQuienAsks, verdadOretoAsks)

            } else {
                InfoLojoLogger.log(INVALID_REQUEST, className())
            }
        }

    }

    private fun seeder(
        yoNuncaAsks: List<AsksBO>, bebeQuienAsks: List<AsksBO>, verdadOretoAsks: List<AsksBO>
    ) {
        InfoLojoLogger.log(MAKE_SEEDERS, className())
        viewModelScope.launch {
            checkDataBaseAndAdd(
                yoNuncaAsks,
                localRepository.selectAllFromYoNunca.asLiveData().value.orEmpty().map { it.toBO() })
            InfoLojoLogger.log("$MAKE_SEEDERS ${Constants.BEBE_QUIEN_TITLE}", className())

            checkDataBaseAndAdd(
                bebeQuienAsks,
                localRepository.selectAllFromBebeQuien.asLiveData().value.orEmpty()
                    .map { it.toBO() })
            InfoLojoLogger.log("$MAKE_SEEDERS ${Constants.VERAD_O_RETO_TITLE}", className())

            checkDataBaseAndAdd(
                verdadOretoAsks,
                localRepository.selectAllFromVerdadOreto.asLiveData().value.orEmpty()
                    .map { it.toBO() })
        }
    }

    private fun checkDataBaseAndAdd(remoteList: List<AsksBO>, localList: List<AsksBO>) {
        viewModelScope.launch {
            remoteList.forEach { asksBOremote ->
                if (localList.map { it.text }.contains(asksBOremote.text).not()) {
                    when (asksBOremote.type) {
                        AskTypeBO.YO_NUNCA -> {
                            localRepository.insertYoNuncaAsk(asksBOremote)
                            InfoLojoLogger.log(Constants.ADDED_YOU_NUNCA_TO_DATABASE, className())
                        }
                        AskTypeBO.VERDAD_O_RETO -> {
                            localRepository.insertVerdadOretoAsk(asksBOremote)
                            InfoLojoLogger.log(
                                Constants.ADDED_VERDAD_O_RETO_TO_DATABASE,
                                className()
                            )

                        }
                        AskTypeBO.BEBE_QUIEN -> {
                            localRepository.insertBebeQuienAsk(asksBOremote)
                            InfoLojoLogger.log(Constants.ADDED_BEBE_QUIEN_TO_DATABASE, className())

                        }
                        AskTypeBO.UNKNOWN -> {
                            InfoLojoLogger.log(Constants.ERROR_UNKNOWN_ASK_TYPE, className())
                            /*no-loop*/
                        }
                    }
                }
            }
        }
    }
}
