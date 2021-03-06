package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var navigation: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        navigation = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homePageStartBtn.setOnClickListener {
            Timber.d("User go to play")
            navigation.navigate(HomePageFragmentDirections.actionHomePageFragmentToHowManyPlayersFragment())
        }

        binding.homePageOptionsBtn.setOnClickListener {
            Timber.d("Go to options menu")
        }
    }
}

