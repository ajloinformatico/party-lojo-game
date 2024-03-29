package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentConfigPlayerManagerBinding
import com.example.party_lojo_game.ui.adapter.ConfigPlayerAdapter
import com.example.party_lojo_game.ui.viewmodel.ConfigPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigPlayerManagerFragment : Fragment(), ConfigPlayerAdapter.HandleBeginToPlay {

    private var binding: FragmentConfigPlayerManagerBinding? = null
    private val viewModel: ConfigPlayerViewModel by viewModels()
    private val args: ConfigPlayerManagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfigPlayerManagerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initList(this.args.numberOfPlayers)
        viewModel.listPlayers.observe(requireActivity()) {
            binding?.pager?.adapter = ConfigPlayerAdapter(this, it, this)
        }
    }

    /**Begin to player*/
    override fun onAllPlayersSelected(players: PlayersBO) {
        findNavController().navigate(
            ConfigPlayerManagerFragmentDirections.actionConfigPlayersManagerToOnPlayerHomeFragment(
                players
            )
        )
    }
}
