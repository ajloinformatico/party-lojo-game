package com.example.party_lojo_game.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.repository.LocalRepository
import com.example.party_lojo_game.data.repository.RemoteRepository
import com.example.party_lojo_game.data.toVo
import com.example.party_lojo_game.ui.vo.AsksVO
import com.example.party_lojo_game.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


const val MAINVIEWMODEL_TIMBER = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository): ViewModel() {

    /** Get all api service data*/
    fun getRemoteResponse() {
        viewModelScope.launch {
            val yoNuncaAsks: List<AsksBO> = remoteRepository.getAllAsksResponse(Constants.YO_NUNCA_URL)
            val bebeQuienAsks: List<AsksBO> = remoteRepository.getAllAsksResponse(Constants.BEBE_QUIEN_URL)
            val verdadOretoAsks: List<AsksBO> = remoteRepository.getAllAsksResponse(Constants.VERDAD_O_RETO_URL)
            if (yoNuncaAsks.isNotEmpty() && bebeQuienAsks.isNotEmpty() && verdadOretoAsks.isNotEmpty()) {
                Timber.d("200 ${yoNuncaAsks.toString()}")
                Timber.d("200 ${bebeQuienAsks.toString()}")
                Timber.d("200 ${verdadOretoAsks.toString()}")
                seeder(yoNuncaAsks, bebeQuienAsks, verdadOretoAsks)


            } else {
                Timber.d("ERROR: Response Failure")
            }
        }

    }

    fun seeder(yoNuncaAsks: List<AsksBO>,
               bebeQuienAsks: List<AsksBO>,
               verdadOretoAsks: List<AsksBO>) {
        Timber.d("make seeder on room database")

        localRepository.selectAllFromYoNunca?.let { localList ->
            checkDataBaseAndAdd(yoNuncaAsks, localList)
        }

        localRepository.selectAllFromBebeQuien?.let { localList ->
            checkDataBaseAndAdd(bebeQuienAsks, localList)
        }

        localRepository.selectAllFromVerdadOreto?.let { localList ->
            checkDataBaseAndAdd(verdadOretoAsks, localList)
        }


    }

    fun checkDataBaseAndAdd(remoteList: List<AsksBO>, localList: List<AsksBO>) {
        viewModelScope.launch {
            remoteList.forEach {
                if (!localList.contains(it)) {
                    when (it.type) {
                        AskTypeBO.YO_NUNCA -> {
                            localRepository.insertYoNuncaAsk(it)
                            Timber.d("Added new yoNunca ask to database")
                        }
                        AskTypeBO.VERDAD_O_RETO -> {
                            localRepository.insertVerdadOretoAsk(it)
                            Timber.d("Added new verdadOreto ask to database")

                        }
                        AskTypeBO.BEBE_QUIEN -> {
                            localRepository.insertBebeQuienAsk(it)
                            Timber.d("Added new bebeQuien ask to database")

                        }
                        AskTypeBO.UNKNONW -> {
                            Timber.d("Error: Unknown ask")
                            /*no-loop*/
                        }
                    }
                }
            }
        }
    }
}