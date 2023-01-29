package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.databinding.FragmentHowManyPLayersBinding
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import com.example.party_lojo_game.utils.className
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowManyPLayersFragment : Fragment() {

    private var binding: FragmentHowManyPLayersBinding? = null
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHowManyPLayersBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            fragmentHowManyPlayersOne.setOnClickListener {
                navigateToConfigPLayerManagerFragment(1)
            }
            fragmentHowManyPlayersTwo.setOnClickListener {
                navigateToConfigPLayerManagerFragment(2)
            }
            fragmentHowManyPlayersThree.setOnClickListener {
                navigateToConfigPLayerManagerFragment(3)
            }
            fragmentHowManyPlayersFour.setOnClickListener {
                navigateToConfigPLayerManagerFragment(4)
            }
            fragmentHowManyPlayersFive.setOnClickListener {
                navigateToConfigPLayerManagerFragment(5)
            }
            fragmentHowManyPlayersSix.setOnClickListener {
                navigateToConfigPLayerManagerFragment(6)
            }
            fragmentHowManyPlayersSeven.setOnClickListener {
                navigateToConfigPLayerManagerFragment(7)
            }
            fragmentHowManyPlayersEight.setOnClickListener {
                navigateToConfigPLayerManagerFragment(8)
            }
            fragmentHowManyPlayersNine.setOnClickListener {
                navigateToConfigPLayerManagerFragment(9)
            }
            fragmentHowManyPlayersTen.setOnClickListener {
                navigateToConfigPLayerManagerFragment(10)
            }
            fragmentHowManyPlayersEleven.setOnClickListener {
                navigateToConfigPLayerManagerFragment(11)
            }
            fragmentHowManyPlayersTwelve.setOnClickListener {
                navigateToConfigPLayerManagerFragment(12)
            }

        }
    }

    private fun navigateToConfigPLayerManagerFragment(n: Int) {
        InfoLojoLogger.log("NAV TO CONFIG WITH $n PLAYERS", className())
        navController.navigate(
            HowManyPLayersFragmentDirections.actionHowManyPlayersFragmentToConfigPlayersManager(
                n
            )
        )
    }
}
