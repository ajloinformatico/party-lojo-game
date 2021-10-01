package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentHowManyPLayersBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HowManyPLayersFragment : Fragment() {

    private lateinit var binding: FragmentHowManyPLayersBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHowManyPLayersBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentHowManyPlayersOne.setOnClickListener {
            navigateToConfigPLayerManagerFragment(1)
        }
        binding.fragmentHowManyPlayersTwo.setOnClickListener {
            navigateToConfigPLayerManagerFragment(2)
        }
        binding.fragmentHowManyPlayersThree.setOnClickListener {
            navigateToConfigPLayerManagerFragment(3)
        }
        binding.fragmentHowManyPlayersFour.setOnClickListener {
            navigateToConfigPLayerManagerFragment(4)
        }
        binding.fragmentHowManyPlayersFive.setOnClickListener {
            navigateToConfigPLayerManagerFragment(5)
        }
        binding.fragmentHowManyPlayersSix.setOnClickListener {
            navigateToConfigPLayerManagerFragment(6)
        }
        binding.fragmentHowManyPlayersSeven.setOnClickListener {
            navigateToConfigPLayerManagerFragment(7)
        }
        binding.fragmentHowManyPlayersEight.setOnClickListener {
            navigateToConfigPLayerManagerFragment(8)
        }
        binding.fragmentHowManyPlayersNine.setOnClickListener {
            navigateToConfigPLayerManagerFragment(9)
        }
        binding.fragmentHowManyPlayersTen.setOnClickListener {
            navigateToConfigPLayerManagerFragment(10)
        }
        binding.fragmentHowManyPlayersEleven.setOnClickListener {
            navigateToConfigPLayerManagerFragment(11)
        }
        binding.fragmentHowManyPlayersTwelve.setOnClickListener {
            navigateToConfigPLayerManagerFragment(12)
        }
    }

    private fun navigateToConfigPLayerManagerFragment(n: Int) {
        Timber.d("NAV TO CONFIG WITH $n PLAYERS")
        navController.navigate(HowManyPLayersFragmentDirections.actionHowManyPlayersFragmentToConfigPlayersManager(n))
    }

}