package com.example.party_lojo_game.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.manager.PlayerAndAsksBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.ui.fragments.ARG_ASK
import com.example.party_lojo_game.ui.fragments.ARG_PLAYER
import com.example.party_lojo_game.ui.fragments.OnPlayAskFragment
import java.lang.Exception
import java.util.*

/**
 * Adapter for onPlayer viewpager
 */
class OnPlayAskPagerAdapter(
    fragment: Fragment,
    private val list: List<PlayerAndAsksBO>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size
    var lastPlayerBO: PlayerBO? = null
    var nextAsksBO: AsksBO? = null

    override fun createFragment(position: Int): Fragment {
        val fragment = OnPlayAskFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(ARG_PLAYER, list[0].playerBO)
            putParcelable(ARG_ASK, list[0].ask)
        }
        return fragment
    }


}