package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.constants.Constants
import com.example.party_lojo_game.data.local.dbo.toBO
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.data.manager.PlayersBO
import com.example.party_lojo_game.databinding.FragmentOnPlayYoNuncaAndBebeQuienBinding
import com.example.party_lojo_game.ui.adapter.OnPlayState
import com.example.party_lojo_game.ui.viewmodel.OnPlayViewModel
import com.example.party_lojo_game.utils.className
import com.example.party_lojo_game.utils.findUserResource
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnPlayYoNuncaAndBebeQuienFragment : Fragment() {

    private lateinit var players: PlayersBO
    private lateinit var type: AskTypeBO
    private val args: OnPlayYoNuncaAndBebeQuienFragmentArgs by navArgs()
    private val onPlayViewModel: OnPlayViewModel by viewModels()
    private var binding: FragmentOnPlayYoNuncaAndBebeQuienBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        players = args.players
        type = args.type
    }

    /**
     * Inflate the layout for this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnPlayYoNuncaAndBebeQuienBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    private fun initViews() {
        binding?.nextPlayerButton?.setOnClickListener {
            onPlayViewModel.manageNextPlayerButtonAction(type)
        }
    }

    private fun initViewModel() {
        onPlayViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OnPlayState.RenderAsk -> showAsk(state.asksBO, state.player)
                is OnPlayState.Loading -> showLoading()
                is OnPlayState.Error -> showError()
            }
        }
        onPlayViewModel.init(players)
        prepareAsks()
    }

    // prepare asks
    private fun prepareAsks() {
        val owner: LifecycleOwner = viewLifecycleOwner
        when (type) {
            AskTypeBO.YO_NUNCA -> onPlayViewModel.getYoNuncaAsks().observe(owner) { asksDBO ->
                onPlayViewModel.renderTypes(type, asksDBO.map { it.toBO() })
            }
            AskTypeBO.BEBE_QUIEN -> onPlayViewModel.getBebeQuienAsks().observe(owner) { asksDBO ->
                onPlayViewModel.renderTypes(type, asksDBO.map { it.toBO() })
            }
            AskTypeBO.VERDAD_O_RETO -> onPlayViewModel.getVerdadORetoAsks()
                .observe(owner) { asksDBO ->
                    onPlayViewModel.renderTypes(type, asksDBO.map { it.toBO() })
                }
            AskTypeBO.UNKNOWN -> {
                // TODO ERROR
                InfoLojoLogger.log(Constants.ERROR_UNKNOWN_ASK_TYPE, className())
            }
        }
    }

    private fun hideLoading() {
        binding?.apply {
            loading.root.gone()
            fragmentOnPlayAsk.show()
            playerName.show()
            fragmentOnPlayAskPlayerImg.show()
            onPlaySectionLabel.show()
            nextPlayerButton.show()
        }
    }

    private fun showLoading() {
        binding?.apply {
            loading.root.show()
            fragmentOnPlayAsk.gone()
            playerName.gone()
            nextPlayerButton.gone()
            onPlaySectionLabel.gone()
            fragmentOnPlayAskPlayerImg.gone()

        }
    }

    private fun showAsk(asksBO: AsksBO?, playerBO: PlayerBO) {
        asksBO?.let { ask ->
            binding?.apply {
                hideLoading()
                onPlaySectionLabel.text = when (type) {
                    AskTypeBO.YO_NUNCA -> resources.getString(R.string.yo_nunca_label_title)
                    AskTypeBO.BEBE_QUIEN -> resources.getString(R.string.bebe_quien_label_title)
                    else -> {
                        showError()
                        ""
                    }
                }
                fragmentOnPlayAsk.text = ask.text
                playerName.text = playerBO.name
                fragmentOnPlayAskPlayerImg.setImageDrawable(
                    requireContext().findUserResource(playerBO.resource)
                )
            }
        } ?: showError()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun showError() {
        // TODO ERROR SCREEN
        InfoLojoLogger.log("Error", className())
    }
}