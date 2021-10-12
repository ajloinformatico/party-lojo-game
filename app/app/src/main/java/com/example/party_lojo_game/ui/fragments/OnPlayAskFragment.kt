package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.databinding.FragmentOnPlayAskBinding

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

    private lateinit var player: PlayerBO
    private lateinit var askBO: AsksBO
    private lateinit var binding: FragmentOnPlayAskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player = it.getSerializable(ARG_PLAYER) as PlayerBO
            askBO = it.getSerializable(ARG_ASK) as AsksBO
        }
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
    }
}