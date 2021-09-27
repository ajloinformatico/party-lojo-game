package com.example.party_lojo_game.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.ui.fragments.ConfigPlayerObjectFragment
import com.example.party_lojo_game.utils.rand

const val numberOfActualPlayer: String = "N_PLAYER"

class ConfigPlayerAdapter(fragment: Fragment, private val numberOfPlayers:Int): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = numberOfPlayers

    override fun createFragment(position: Int): Fragment {
        val fragment = ConfigPlayerObjectFragment()
        fragment.arguments = Bundle().apply {
            putSerializable(numberOfActualPlayer, PlayerBO("user${position + 1}", "user${rand(1,12)}.png",position+1))
        }
        return fragment
    }
}