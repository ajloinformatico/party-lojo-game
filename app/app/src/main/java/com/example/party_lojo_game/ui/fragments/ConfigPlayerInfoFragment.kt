package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.databinding.FragmentConfigPlayerInfoBinding
import com.example.party_lojo_game.ui.adapter.MAX_PLAYERS
import com.example.party_lojo_game.ui.adapter.NEXT_PLAYER
import com.example.party_lojo_game.ui.adapter.ONE_PLAYER
import com.example.party_lojo_game.utils.hide
import com.example.party_lojo_game.utils.show

class ConfigPlayerInfoFragment : Fragment() {

    private lateinit var binding: FragmentConfigPlayerInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfigPlayerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ONE_PLAYER) }?.apply {
            val onePlayer = getBoolean(ONE_PLAYER)
            if (onePlayer) {
                binding.configPlayerInfoMorePlayerTitle.visibility = View.GONE
                binding.configPlayerInfoOnePlayerTitle.visibility = View.VISIBLE
                binding.configPlayerInfoMorePlayerExplain.visibility = View.GONE
                binding.configPlayerInfoOnePlayerExplain.visibility = View.VISIBLE
            } else {
                binding.configPlayerInfoMorePlayerTitle.visibility = View.VISIBLE
                binding.configPlayerInfoOnePlayerTitle.visibility = View.GONE
                binding.configPlayerInfoMorePlayerExplain.visibility = View.VISIBLE
                binding.configPlayerInfoOnePlayerExplain.visibility = View.GONE
            }
        }
    }
}