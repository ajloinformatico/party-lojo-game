package com.example.party_lojo_game.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.repository.RemoteRepository
import com.example.party_lojo_game.data.toVo
import com.example.party_lojo_game.ui.vo.AsksVO
import com.example.party_lojo_game.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


const val MAINVIEWMODEL_TIMBER = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteRepository): ViewModel() {



    /** Get all api service data*/
    fun getRemoteResponse(): Unit{
        viewModelScope.launch {
            val yoNuncaAsks: List<AsksBO> = repository.getAllAsksResponse(Constants.YO_NUNCA_URL)
            val bebeQuienAsks: List<AsksBO> = repository.getAllAsksResponse(Constants.BEBE_QUIEN_URL)
            val verdadOretoAsks: List<AsksBO> = repository.getAllAsksResponse(Constants.VERDAD_O_RETO_URL)

            if (yoNuncaAsks.isNotEmpty()) {
                Log.d(MAINVIEWMODEL_TIMBER, yoNuncaAsks.toString())
                Log.d(MAINVIEWMODEL_TIMBER, bebeQuienAsks.toString())
                Log.d(MAINVIEWMODEL_TIMBER, verdadOretoAsks.toString())
            } else {
                Log.d(MAINVIEWMODEL_TIMBER, "ERROR: Response Failure")
            }
        }

    }

    /** Conver listBo to listBo*/
    fun List<AsksBO>.toVo(): List<AsksVO> {
        val returnList = mutableListOf<AsksVO>()
        this.forEach {
            returnList.add(it.toVo())
        }
        return returnList
    }

    fun List<AsksVO>.saveOnDataBase(): Unit {
    }

}