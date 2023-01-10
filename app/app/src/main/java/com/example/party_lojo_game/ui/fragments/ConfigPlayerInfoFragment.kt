package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.party_lojo_game.databinding.FragmentConfigPlayerInfoBinding
import com.example.party_lojo_game.ui.adapter.ONE_PLAYER
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.show

class ConfigPlayerInfoFragment : Fragment() {

    private var binding: FragmentConfigPlayerInfoBinding? = null

    /**
     * Inflate the layout for this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigPlayerInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ONE_PLAYER) }?.let { bundle ->
            val onePlayer = bundle.getBoolean(ONE_PLAYER)
            binding?.apply {
                if (onePlayer) {
                    configPlayerInfoMorePlayerTitle.gone()
                    configPlayerInfoOnePlayerTitle.show()
                    configPlayerInfoMorePlayerExplain.gone()
                    configPlayerInfoOnePlayerExplain.show()
                } else {
                    configPlayerInfoMorePlayerTitle.show()
                    configPlayerInfoOnePlayerTitle.gone()
                    configPlayerInfoMorePlayerExplain.show()
                    configPlayerInfoOnePlayerExplain.gone()
                }
            }
        }
    }
}
