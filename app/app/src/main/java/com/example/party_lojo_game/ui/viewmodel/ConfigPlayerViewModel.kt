package com.example.party_lojo_game.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.utils.rand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ConfigPlayerViewModel @Inject constructor() : ViewModel() {

    private var _numberOfPlayers = 0
    private val _listPlayers = MutableLiveData<PlayersBO>()
    val listPlayers: LiveData<PlayersBO> = _listPlayers


    fun initList(numberOfPlayers: Int) {
        _numberOfPlayers = numberOfPlayers
        val list = mutableListOf<PlayerBO>()

        //Prepare list of default values
        for (i in 1..numberOfPlayers) {
            list.add(PlayerBO("user $i", "user${rand(1,47)}.png", i))
        }
        changeListPlayers(PlayersBO(list))
    }

    fun changeListPlayers(playersBO: PlayersBO) {
        _listPlayers.value = playersBO
        Log.d("TAG:::", playersBO.toString())
    }




    fun haveNext(actualPosition: Int): Boolean = _numberOfPlayers != 0 && actualPosition < _numberOfPlayers

}