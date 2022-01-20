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
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentOnPlayeHomeBinding
import com.example.party_lojo_game.utils.Constants
import com.example.party_lojo_game.utils.getImage

class OnPlayHomeFragment : Fragment() {

    private lateinit var binding: FragmentOnPlayeHomeBinding
    private lateinit var players: PlayersBO
    private val args: OnPlayHomeFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnPlayeHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderPlayersIcons(requireContext(), args.players)
        renderButtonsActions()

    }

    private fun renderButtonsActions() {
        binding.fragmentOnPlayeHomeBebeQuien.setOnClickListener {
            navigateToOnPLay(Constants.BEBE_QUIEN_TITLE)
        }
        binding.fragmentOnPlayHomeYoNunca.setOnClickListener {
            navigateToOnPLay(Constants.YO_NUNCA_TITLE)
        }
        binding.fragmentOnPlayHomeVerdadOReto.setOnClickListener {
            navigateToOnPLay(Constants.VERAD_O_RETO_TITLE)
        }
    }

    private fun navigateToOnPLay(title: String) {
        navController.navigate(
            OnPlayHomeFragmentDirections.actionOnPLayHomeMannagerToOnPlayAskMannager(
                title,
                players
            )
        )
    }

    private fun renderPlayersIcons(context: Context, players: PlayersBO) {
        players.players.forEachIndexed { index, playerBO ->
            when (index) {

                0 -> {
                    binding.fragmentOnPlayHomePlayer1.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer1.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer2.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer3.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer4.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer5.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer6.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                1 -> {
                    binding.fragmentOnPlayHomePlayer2.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer2.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer3.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer4.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer5.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer6.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                2 -> {
                    binding.fragmentOnPlayHomePlayer3.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer3.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer4.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer5.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer6.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                3 -> {
                    binding.fragmentOnPlayHomePlayer4.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer4.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer5.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer6.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                4 -> {
                    binding.fragmentOnPlayHomePlayer5.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer5.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer6.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                5 -> {
                    binding.fragmentOnPlayHomePlayer6.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer6.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer7.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                6 -> {
                    binding.fragmentOnPlayHomePlayer7.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer7.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer8.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                7 -> {
                    binding.fragmentOnPlayHomePlayer8.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer8.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer9.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                8 -> {
                    binding.fragmentOnPlayHomePlayer9.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer9.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer10.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                9 -> {
                    binding.fragmentOnPlayHomePlayer10.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer10.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer11.visibility = View.GONE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                10 -> {
                    binding.fragmentOnPlayHomePlayer11.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer11.visibility = View.VISIBLE
                    binding.fragmentOnPlayHomePlayer12.visibility = View.GONE
                }

                11 -> {
                    binding.fragmentOnPlayHomePlayer12.setImageDrawable(
                        getImage(
                            playerBO.resource,
                            context
                        )
                    )
                    binding.fragmentOnPlayHomePlayer12.visibility = View.VISIBLE
                }
            }
        }
    }
}