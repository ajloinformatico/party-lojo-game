package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.AsksBOList
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentOnPlayAskMannagerBinding
import com.example.party_lojo_game.ui.viewmodel.OnPlayViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnPlayAskMannagerFragment : Fragment() {
    private lateinit var asks: AsksBOList
    private lateinit var players: PlayersBO
    private lateinit var navigation: NavController
    private lateinit var binding: FragmentOnPlayAskMannagerBinding
    private val viewModel: OnPlayViewModel by viewModels()
    // private val args: OnPlayAskMannagerFragmentArgs by NavArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnPlayAskMannagerBinding.inflate(inflater, container, false)
        navigation = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

/**
 * arguments?.let {
 * asks = it.getSerializable(ARG_ON_PLAY_ASKS_MANAGER) as AsksBOList
 * players = it.getString(ARG_ON_PLAY_PLAYERS_MANAGER) as PlayersBO
}
 */

