package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.databinding.ConfigPlayerObjectFragmentBinding
import com.example.party_lojo_game.ui.adapter.ConfigImageAddImageAdapter
import com.example.party_lojo_game.ui.adapter.MAX_PLAYERS
import com.example.party_lojo_game.ui.adapter.NEXT_PLAYER
import com.example.party_lojo_game.utils.createImageList
import com.example.party_lojo_game.utils.findUserResource
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.show


class ConfigPlayerObjectFragment(listener: HandleNextPlayer) : Fragment() {

    interface HandleNextPlayer {
        fun nextPlayer(playerBO: PlayerBO)
        fun beginToPlay()
    }

    companion object {
        fun newInstance(
            nextPlayer: PlayerBO,
            maxPlayer: Int,
            listener: HandleNextPlayer
        ) = ConfigPlayerObjectFragment(listener).apply {
            arguments = Bundle().apply {
                putParcelable(NEXT_PLAYER, nextPlayer)
                putInt(MAX_PLAYERS, maxPlayer)
            }
        }
    }

    private var binding: ConfigPlayerObjectFragmentBinding? = null

    private val nextPlayer: PlayerBO? by lazy {
        arguments?.getParcelable(NEXT_PLAYER)
    }

    private val maxPlayers: Int by lazy {
        arguments?.getInt(MAX_PLAYERS) ?: 0
    }

    private val handleNextPlayer: HandleNextPlayer = listener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfigPlayerObjectFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /**Control for scrollview
         * https://medium.com/@goforbg/horizontal-recyclerview-inside-viewpager2-handling-scrolls-982da4aa454b
         * */
        val scrollListener = object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action

                return if (binding?.configPlayerCustomAlertRecycler?.canScrollHorizontally(
                        RecyclerView.FOCUS_FORWARD
                    ) == true
                ) {
                    when (action) {
                        MotionEvent.ACTION_MOVE -> rv.parent
                            .requestDisallowInterceptTouchEvent(true)
                    }
                    false

                } else {
                    when (action) {
                        MotionEvent.ACTION_MOVE -> rv.parent
                            .requestDisallowInterceptTouchEvent(false)
                    }

                    binding?.configPlayerCustomAlertRecycler?.removeOnItemTouchListener(this)
                    true
                }
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }

        nextPlayer?.let { player ->
            checkAndShowOrHideDragAndButtonInfo(player, maxPlayers)

            binding?.apply {
                configPlayerPlayerNameTxt.setText(player.name)
                configPlayerImg.setImageDrawable(requireContext().findUserResource(player.resource))

                configPlayerPlayerNameTxt.setOnFocusChangeListener { v, hasFocus ->
                    val text = configPlayerPlayerNameTxt.text.toString()
                    if (!hasFocus && text != player.name && text.isNotEmpty()) {

                        handleNextPlayer.nextPlayer(
                            PlayerBO(
                                text,
                                player.resource,
                                player.position
                            )
                        )
                    }
                }

                configPlayerImg.setOnClickListener {
                    configPlayerCustomAlertRecycler.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    configPlayerCustomAlertRecycler.adapter = ConfigImageAddImageAdapter(
                        player.resource,
                        ::onImageSelectedActionManager
                    ).apply {
                        submitList(createImageList())
                    }
                    configPlayerCustomAlertRecycler.addOnItemTouchListener(scrollListener)
                    configPlayerCustomAlert.show()
                }

                configPlayerStartBtn.setOnClickListener {
                    handleNextPlayer.beginToPlay()
                }
            }
        }
    }


    private fun checkAndShowOrHideDragAndButtonInfo(player: PlayerBO, maxPlayers: Int) {
        binding?.apply {
            if (player.position >= maxPlayers) {
                configPlayerNextLabel.gone()
                configPlayerStartBtn.show()
                configPlayerStartBtnLabel.show()
            } else {
                configPlayerNextLabel.show()
                configPlayerStartBtn.gone()
                configPlayerStartBtnLabel.gone()
            }
        }
    }

    private fun onImageSelectedActionManager(image: String) {
        handleNextPlayer.nextPlayer(
            PlayerBO(
                binding?.configPlayerPlayerNameTxt?.text.toString(),
                image,
                nextPlayer?.position ?: 0
            )
        )
        binding?.apply {
            configPlayerImg.setImageDrawable(requireContext().findUserResource(image))
            configPlayerCustomAlert.gone()
        }
    }
}
