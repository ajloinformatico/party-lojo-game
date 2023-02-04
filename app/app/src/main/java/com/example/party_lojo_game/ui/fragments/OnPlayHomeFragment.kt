package com.example.party_lojo_game.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentOnPlayeHomeBinding
import com.example.party_lojo_game.utils.findUserResource
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.show

class OnPlayHomeFragment : Fragment() {

    private var binding: FragmentOnPlayeHomeBinding? = null
    private lateinit var players: PlayersBO
    private val args: OnPlayHomeFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        players = args.players
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnPlayeHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderPlayersIcons(requireContext(), args.players)
        renderButtonsActions()

    }

    private fun renderButtonsActions() {
        binding?.apply {
            fragmentOnPlayeHomeBebeQuien.setOnClickListener {
                navigateToOnPLay(AskTypeBO.BEBE_QUIEN)
            }
            fragmentOnPlayHomeYoNunca.setOnClickListener {
                navigateToOnPLay(AskTypeBO.YO_NUNCA)
            }
            fragmentOnPlayHomeVerdadOReto.setOnClickListener {
                navigateToOnPLay(AskTypeBO.VERDAD_O_RETO)
            }
        }
    }

    private fun navigateToOnPLay(type: AskTypeBO) {
        navController.navigate(
            OnPlayHomeFragmentDirections.actionOnPLayHomeMannagerToOnPlayToNuncaAndbebeQuienFragment(
                players,
                type
            )
        )
    }

    private fun renderPlayersIcons(context: Context, players: PlayersBO) {
        players.players.forEachIndexed { index, playerBO ->
            binding?.apply {

                when (index) {
                    0 -> {
                        fragmentOnPlayHomePlayer1.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer1.show()
                        fragmentOnPlayHomePlayer2.gone()
                        fragmentOnPlayHomePlayer3.gone()
                        fragmentOnPlayHomePlayer4.gone()
                        fragmentOnPlayHomePlayer5.gone()
                        fragmentOnPlayHomePlayer6.gone()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    1 -> {
                        fragmentOnPlayHomePlayer2.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer2.show()
                        fragmentOnPlayHomePlayer3.gone()
                        fragmentOnPlayHomePlayer4.gone()
                        fragmentOnPlayHomePlayer5.gone()
                        fragmentOnPlayHomePlayer6.gone()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    2 -> {
                        fragmentOnPlayHomePlayer3.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer3.show()
                        fragmentOnPlayHomePlayer4.gone()
                        fragmentOnPlayHomePlayer5.gone()
                        fragmentOnPlayHomePlayer6.gone()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    3 -> {
                        fragmentOnPlayHomePlayer4.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer4.show()
                        fragmentOnPlayHomePlayer5.gone()
                        fragmentOnPlayHomePlayer6.gone()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    4 -> {
                        fragmentOnPlayHomePlayer5.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer5.show()
                        fragmentOnPlayHomePlayer6.gone()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    5 -> {
                        fragmentOnPlayHomePlayer6.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer6.show()
                        fragmentOnPlayHomePlayer7.gone()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    6 -> {
                        fragmentOnPlayHomePlayer7.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer7.show()
                        fragmentOnPlayHomePlayer8.gone()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    7 -> {
                        fragmentOnPlayHomePlayer8.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer8.show()
                        fragmentOnPlayHomePlayer9.gone()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    8 -> {
                        fragmentOnPlayHomePlayer9.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer9.show()
                        fragmentOnPlayHomePlayer10.gone()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    9 -> {
                        fragmentOnPlayHomePlayer10.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer10.show()
                        fragmentOnPlayHomePlayer11.gone()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    10 -> {
                        fragmentOnPlayHomePlayer11.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer11.show()
                        fragmentOnPlayHomePlayer12.gone()
                    }

                    11 -> {
                        fragmentOnPlayHomePlayer12.setImageDrawable(
                            context.findUserResource(playerBO.resource)
                        )
                        fragmentOnPlayHomePlayer12.show()
                    }
                }
            }
        }
    }
}
