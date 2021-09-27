package com.example.party_lojo_game.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.ConfigPlayerObjectFragmentBinding
import com.example.party_lojo_game.ui.adapter.numberOfActualPlayer
import com.example.party_lojo_game.ui.callbacks.HandleNextPlayer


class ConfigPlayerObjectFragment : Fragment() {

    private lateinit var binding: ConfigPlayerObjectFragmentBinding
    private lateinit var handleNextPlayer: HandleNextPlayer
    private lateinit var player: PlayerBO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConfigPlayerObjectFragmentBinding.inflate(inflater, container, false)
        player = savedInstanceState?.getSerializable(numberOfActualPlayer) as PlayerBO
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.configPlayerImg.setImageDrawable(getRandomImage())


    }

    private fun getRandomImage(): Drawable? {
        return when (player.resource) {
            "user1.png" -> AppCompatResources.getDrawable(requireContext(), R.mipmap.user1)

            "user2.png" -> AppCompatResources.getDrawable(requireContext(), R.mipmap.user2)

            else -> AppCompatResources.getDrawable(requireContext(), R.mipmap.user1)
        }
    }

}