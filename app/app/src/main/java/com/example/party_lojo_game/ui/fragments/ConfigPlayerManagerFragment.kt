package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentConfigPlayerManagerBinding
import com.example.party_lojo_game.ui.adapter.ConfigPlayerAdapter
import com.example.party_lojo_game.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigPlayerManagerFragment : Fragment() {

    private lateinit var binding: FragmentConfigPlayerManagerBinding
    private lateinit var navigation: NavController
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
        binding.pager.adapter = ConfigPlayerAdapter(this,args.numberOfPlayers)
    }

}