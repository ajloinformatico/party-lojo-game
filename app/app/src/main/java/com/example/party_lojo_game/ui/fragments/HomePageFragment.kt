package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentHomePageBinding
import com.example.party_lojo_game.utils.extensions.className
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private var binding: FragmentHomePageBinding? = null
    private lateinit var navigation: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        navigation = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            homePageStartBtn.setOnClickListener {
                InfoLojoLogger.log("User go to play", className())
                navigation.navigate(HomePageFragmentDirections.actionHomePageFragmentToHowManyPlayersFragment())
            }

            homePageOptionsBtn.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
                InfoLojoLogger.log("Go to options menu", className())
            }
            addNavigationItemSelectedListener()
        }
    }

    private fun addNavigationItemSelectedListener() {
        binding?.apply {
            navView.bringToFront()
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.title) {
                    resources.getString(R.string.add_new_questions) -> {
                        // TODO
                    }
                    resources.getString(R.string.add_your_own_images) -> {
                        // TODO
                    }
                    resources.getString(R.string.edit_your_questions) -> {
                        // TODO
                    }
                }
                Toast.makeText(context, menuItem.title, Toast.LENGTH_SHORT).show()
                InfoLojoLogger.log(menuItem.title.toString(), className())
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}
