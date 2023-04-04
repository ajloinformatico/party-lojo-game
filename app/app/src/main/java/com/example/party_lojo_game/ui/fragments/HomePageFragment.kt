package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentHomePageBinding
import com.example.party_lojo_game.utils.extensions.className
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import dagger.hilt.android.AndroidEntryPoint

private const val MENU_NAVIGATION_DELAY: Long = 500L

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
                InfoLojoLogger.log("User Go to options menu", className())
                drawerLayout.openDrawer(GravityCompat.START)
            }

            infolojoWebView.setOnClickListener {
                InfoLojoLogger.log("User go to infolojo", className())
                navigation.navigate(HomePageFragmentDirections.actionHomePageFragmentToInfolojoWebView())
            }
            addNavigationItemSelectedListener()
        }
    }

    private fun addNavigationItemSelectedListener() {
        binding?.apply {
            navView.bringToFront()
            navView.setNavigationItemSelectedListener { menuItem ->
                val menuTitle = menuItem.title.toString()
                InfoLojoLogger.log(menuTitle, className())
                drawerLayout.closeDrawer(GravityCompat.START)
                drawerLayout.postDelayed({
                    navigateMenu(menuTitle)
                }, MENU_NAVIGATION_DELAY)
            }
        }
    }

    /** do navigation */
    private fun navigateMenu(menuTitle: String): Boolean {
        navigation.navigate(
            when (menuTitle) {
                resources.getString(R.string.add_new_questions) ->
                    HomePageFragmentDirections.actionHomePageFragmentToAddQuestionFragment()
                resources.getString(R.string.add_your_own_images) ->
                    HomePageFragmentDirections.actionHomePageFragmentToAddImageFragment()
                resources.getString(R.string.edit_your_questions) ->
                    HomePageFragmentDirections.actionHomePageFragmentToEditQuestionFragment()
                else ->
                    HomePageFragmentDirections.actionHomePageFragmentToEditQuestionFragment()
            }
        )
        return true
    }
}
