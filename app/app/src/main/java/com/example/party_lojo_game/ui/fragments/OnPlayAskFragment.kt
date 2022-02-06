package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentOnPlayAskBinding
import com.example.party_lojo_game.ui.viewmodel.OnPlayViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_PLAYER = "ARG_PLAYER"
const val ARG_ASK = "ARG_ASK"

/**
 * A simple [Fragment] subclass.
 * Use the [OnPlayAskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnPlayAskFragment : Fragment() {

    private lateinit var players: PlayersBO
    private lateinit var type: AskTypeBO
    private val args: OnPlayAskFragmentArgs by navArgs()
    private val onPlayViewModel: OnPlayViewModel by viewModels()
    private lateinit var binding: FragmentOnPlayAskBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        players = args.players
        type = args.type
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnPlayAskBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: SET VIEW
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        //Todo set ToolbarTitle
    }

    private fun initViewModel() {
        onPlayViewModel.init(type, players)
    }
}