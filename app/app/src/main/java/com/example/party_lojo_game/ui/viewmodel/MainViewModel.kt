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

        localRepository.selectAllFromYoNunca?.let {
            checkDataBaseAndAdd(yoNuncaAsks, it, Constants.YO_NUNCA_DTO_TYPE)
        }

        localRepository.selectAllFromBebeQuien?.let {
            checkDataBaseAndAdd(bebeQuienAsks, it, Constants.BEBE_QUIEN_DTO_TYPE)
        }

        localRepository.selectAllFromVerdadOreto?.let {
            checkDataBaseAndAdd(verdadOretoAsks, it, Constants.VERDAD_O_RETO_TYPE)
        }

//        localRepository.selectAllFromBebeQuien
//
//        allMonumentsFromApi?.monuments?.forEach { it ->
//            if (!monumentsIdFromDatabase.contains(it.id)) {
//                repository.insertMonument(it.toBO())
//                val ownerId = it.id
//
//                it.images?.forEach { imageDTO ->
//                    repository.insertImage(imageDTO.toBO(ownerId?:0L))
//                }
//            }
//        }

    }

    fun checkDataBaseAndAdd(remoteList: List<AsksBO>, localList: List<AsksBO>, type: String) {
        viewModelScope.launch {
            remoteList.forEach {
                if (!localList.contains(it)) {
                    when (it.type) {
                        AskTypeBO.YO_NUNCA -> {
                            localRepository.insertBebeQuienAsk(it)
                        }
                        AskTypeBO.VERDAD_O_RETO -> {
                            //insert into database
                        }
                        AskTypeBO.BEBE_QUIEN -> {
                            //insert into database
                        }
                        AskTypeBO.UNKNONW -> {
                            /*no-loop*/
                        }
                    }
                }
            }
        }
    }
}