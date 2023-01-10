package com.example.party_lojo_game.ui.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.ui.fragments.ConfigPlayerInfoFragment
import com.example.party_lojo_game.ui.fragments.ConfigPlayerObjectFragment

const val NEXT_PLAYER: String = "N_PLAYER"
const val MAX_PLAYERS: String = "MAX_PLAYERS"
const val ONE_PLAYER: String = "ONE_PLAYER"

class ConfigPlayerAdapter(
    fragment: Fragment,
    private val players: PlayersBO,
    handleBeginBeginToPlayer: HandleBeginToPlay,
) : FragmentStateAdapter(fragment), ConfigPlayerObjectFragment.HandleNextPlayer {

    private val listener = handleBeginBeginToPlayer
    private var playersChanged: MutableList<PlayerBO> = players.players as MutableList<PlayerBO>
    // TODO CHECK TO REMOVE var
    private var isGalleryOpen: Boolean = false
    //Note: Boolean value to load first screen
    private var firstElement: Boolean = true

    interface HandleBeginToPlay {
        fun onAllPlayersSelected(players: PlayersBO)
    }

    override fun getItemCount(): Int = players.players.size + 1

    override fun createFragment(position: Int): Fragment =

        if (position != 0) {
            ConfigPlayerObjectFragment.newInstance(
                players.players[position-1],
                itemCount - 1,
                this
            )
        } else {
            val fragment = ConfigPlayerInfoFragment()
            fragment.arguments = Bundle().apply {
                if (itemCount > 2) {
                    putBoolean(ONE_PLAYER, false)
                } else {
                    putBoolean(ONE_PLAYER, true)
                }
            }
            firstElement = false
            fragment
        }


    /**Notify to manager to change page*/
    override fun nextPlayer(playerBO: PlayerBO) {
        val newList = mutableListOf<PlayerBO>()
        players.players.forEach {
            if (it.position != playerBO.position) {
                newList.add(it)
            } else {
                newList.add(playerBO)
            }
        }
        playersChanged = newList
        Log.d("TAG::UPDATING LIST", playersChanged.toString())
    }

    /**Begin to play*/
    override fun beginToPlay() {
        listener.onAllPlayersSelected(PlayersBO(playersChanged))
    }
}