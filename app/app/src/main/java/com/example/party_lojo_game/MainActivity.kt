package com.example.party_lojo_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.example.party_lojo_game.databinding.ActivityMainBinding
import com.example.party_lojo_game.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //find nav controller
        navController = Navigation.findNavController(this,
        R.id.main_activity__nav_host_fragment)

        //Set ActionBar
        setSupportActionBar(binding.activityMainCustomToolbar.customToolbar)
        binding.activityMainCustomToolbar.customToolbar.collapseIcon =
            AppCompatResources.getDrawable(this, R.drawable.ic_icon)
        supportActionBar?.title = ""

        //Get response and save on database
        mainViewModel.getRemoteResponse()

        //Change title
        navController.addOnDestinationChangedListener{ _, destionation, _ ->
            when (destionation.id) {
                R.id.homePageFragment -> {
                    this.binding.activityMainCustomToolbar.customToolbarTitle.text = this.getString(R.string.title)
                }
            }
        }
    }



    //TODO INSTANCE VIEWMODEL AND GET AND SAVE INFO FROM APISERVICE INTO DATABASE
    //TODO IT MUST EXECUTE WHEN APP RUNS AND CHECK IF CONTENT IS THE SAME THAT DATABASE
    //TODO NAV AND LAYOUTS
}