package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.databinding.FragmentHomePageBinding
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import com.example.party_lojo_game.utils.className
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private var binding: FragmentHomePageBinding? = null
    private lateinit var navigation: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        navigation = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.homePageStartBtn?.setOnClickListener {
            InfoLojoLogger.log("User go to play", className())
            navigation.navigate(HomePageFragmentDirections.actionHomePageFragmentToHowManyPlayersFragment())
        }

        binding?.homePageOptionsBtn?.setOnClickListener {
            InfoLojoLogger.log("Go to options menu", className())
        }
    }
}
