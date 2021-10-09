package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.manager.PlayerBO
import com.example.party_lojo_game.databinding.ConfigPlayerObjectFragmentBinding
import com.example.party_lojo_game.ui.adapter.ConfigImageAddImageAdapter
import com.example.party_lojo_game.ui.adapter.MAX_PLAYERS
import com.example.party_lojo_game.ui.adapter.NEXT_PLAYER
import com.example.party_lojo_game.utils.createImageList
import com.example.party_lojo_game.utils.getImage


class ConfigPlayerObjectFragment(listener: HandleNextPlayer) : Fragment(), ConfigImageAddImageAdapter.ConfigImageSelectedImage {

    interface HandleNextPlayer {
        fun nextPlayer(playerBO: PlayerBO)
        fun beginToPlay()
    }

    private lateinit var binding: ConfigPlayerObjectFragmentBinding
    private lateinit var player: PlayerBO
    private val handleNextPlayer: HandleNextPlayer = listener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConfigPlayerObjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val listener: ConfigImageAddImageAdapter.ConfigImageSelectedImage = this

        /**Control for scrollview
         * https://medium.com/@goforbg/horizontal-recyclerview-inside-viewpager2-handling-scrolls-982da4aa454b
         * */
        val scrollListener = object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action
                return if (binding.configPlayerCustomAlertRecycler.canScrollHorizontally(RecyclerView.FOCUS_FORWARD)) {
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
                    binding.configPlayerCustomAlertRecycler.removeOnItemTouchListener(this)
                    true
                }
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }

        arguments?.takeIf { it.containsKey(NEXT_PLAYER) && it.containsKey(MAX_PLAYERS) }?.apply {
            player = getSerializable(NEXT_PLAYER) as PlayerBO
            val max: Int = getInt(MAX_PLAYERS)
            checkAndShowOrHideDragAndButtonInfo(player, max)

            //Gallery
            val adapter = ConfigImageAddImageAdapter(listener, player.resource)
            //LinearLayoutManager for mini gallery
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            binding.configPlayerPlayerNameTxt.setText(player.name)
            binding.configPlayerImg.setImageDrawable(getImage(player.resource, requireContext()))

            binding.configPlayerPlayerNameTxt.setOnFocusChangeListener { v, hasFocus ->
                val text = binding.configPlayerPlayerNameTxt.text.toString()
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

            binding.configPlayerImg.setOnClickListener {
                binding.configPlayerCustomAlert.visibility = View.VISIBLE
                binding.configPlayerCustomAlertRecycler.layoutManager = linearLayoutManager
                binding.configPlayerCustomAlertRecycler.adapter = adapter
                binding.configPlayerCustomAlertRecycler.addOnItemTouchListener(scrollListener)
                adapter.submitList(createImageList())
            }

            binding.configPlayerStartBtn.setOnClickListener {
                handleNextPlayer.beginToPlay()
            }

        }
    }


    private fun checkAndShowOrHideDragAndButtonInfo(player: PlayerBO, maxPlayers:Int) {
        if (player.position >= maxPlayers) {
            binding.configPlayerNextLabel.visibility = View.GONE
            binding.configPlayerStartBtn.visibility = View.VISIBLE
            binding.configPlayerStartBtnLabel.visibility = View.VISIBLE
        } else {
            binding.configPlayerNextLabel.visibility = View.VISIBLE
            binding.configPlayerStartBtn.visibility = View.GONE
            binding.configPlayerStartBtnLabel.visibility = View.GONE
        }
    }

    override fun onItemSelect(image: String) {
        handleNextPlayer.nextPlayer(
            PlayerBO(
                binding.configPlayerPlayerNameTxt.text.toString(),
                image,
                player.position
            )
        )
        binding.configPlayerImg.setImageDrawable(getImage(image, requireContext()))
        binding.configPlayerCustomAlert.visibility = View.GONE
    }
}
