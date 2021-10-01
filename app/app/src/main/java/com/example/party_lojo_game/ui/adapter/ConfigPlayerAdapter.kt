package com.example.party_lojo_game.ui.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.ui.fragments.ConfigPlayerObjectFragment

const val NEXT_PLAYER: String = "N_PLAYER"
const val MAX_PLAYERS: String = "MAX_PLAYERS"

class ConfigPlayerAdapter(
    fragment: Fragment,
    private val players: PlayersBO,
    handleNextPlayer: HandleToManagerNextPlayer,
) : FragmentStateAdapter(fragment), ConfigPlayerObjectFragment.HandleNextPlayer {

    private val listener = handleNextPlayer
    private var playersChanged: MutableList<PlayerBO> = mutableListOf()
    private var isGalleryOpen: Boolean = false

    interface HandleToManagerNextPlayer {
        fun onAllPlayersSelected(players: PlayersBO, maxOfPlayers: Int)
    }

    override fun getItemCount(): Int = players.players.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ConfigPlayerObjectFragment(this)
        fragment.arguments = Bundle().apply {
            putSerializable(NEXT_PLAYER, players.players[position])
            putInt(MAX_PLAYERS, itemCount)
        }
        return fragment
    }

    /**Notify to manager to change page*/
    override fun nextPlayer(playerBO: PlayerBO) {
        val newList = playersChanged
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
}