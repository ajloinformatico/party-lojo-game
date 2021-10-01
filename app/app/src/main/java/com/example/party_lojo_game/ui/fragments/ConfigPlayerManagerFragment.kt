package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentConfigPlayerManagerBinding
import com.example.party_lojo_game.ui.adapter.ConfigPlayerAdapter
import com.example.party_lojo_game.ui.viewmodel.ConfigPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ConfigPlayerManagerFragment: Fragment(), ConfigPlayerAdapter.HandleToManagerNextPlayer{

    private lateinit var binding: FragmentConfigPlayerManagerBinding
    private lateinit var navigation: NavController
    private val viewModel: ConfigPlayerViewModel by viewModels()
    private val args: ConfigPlayerManagerFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentConfigPlayerManagerBinding.inflate(inflater, container, false)
        navigation = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initList(this.args.numberOfPlayers)
        viewModel.listPlayers.observe(requireActivity(), {
            binding.pager.adapter = ConfigPlayerAdapter(this, it, this)
        })
    }

    override fun onAllPlayersSelected(players: PlayersBO, maxOfPlayers: Int) {
        TODO("Not yet implemented")
        //TODO CARGA LA PAGINA DE JUEGO
    }
}