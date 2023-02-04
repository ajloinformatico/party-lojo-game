package com.example.party_lojo_game

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.party_lojo_game.data.constants.Constants
import com.example.party_lojo_game.databinding.ActivityMainBinding
import com.example.party_lojo_game.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var binding: ActivityMainBinding? = null
    private var location: String = ""
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.root?.let(::setContentView)

        //find nav controller
        navController = Navigation.findNavController(
            this, R.id.main_activity__nav_host_fragment
        )

//        // TODO WHY I DO IT ????
//        // Set ActionBar
//        setSupportActionBar(binding.activityMainCustomToolbar.customToolbar)
//        binding.activityMainCustomToolbar.customToolbar.collapseIcon =
//            ContextCompact.getDrawable(this, R.drawable.ic_icon)
//        supportActionBar?.title = ""

        //Get response and save on database
        mainViewModel.getRemoteResponse()

        //Change title
        navController.addOnDestinationChangedListener { _, destionation, _ ->
            when (destionation.id) {
                R.id.homePageFragment -> {
                    location = Constants.HOME_PAGE_LOCATION
                }

                R.id.howManyPlayersFragment -> {
                    location = ""
                }

                R.id.configPlayersManager -> {
                    location = Constants.CONFIG_PLAYERS_LOCATION
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        when (this.location) {
            Constants.HOME_PAGE_LOCATION -> {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage(this.resources.getString(R.string.exit_app))
                    .setTitle(this.resources.getString(R.string.exit_app_title))
                    .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_warning))
                    .setPositiveButton(R.string.dialog_si) { _, _ ->
                        // TODO REPLACE THIS DEPRECATED
                        super.onBackPressed()
                        finishActivity(0)
                    }.setNegativeButton(R.string.dialog_no) { _, _ -> /*no-loop*/ }
                dialog.create()
                dialog.show()


            }
            Constants.ON_GAME_PLAY -> {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage(this.resources.getString(R.string.on_play_back))
                    .setTitle(this.resources.getString(R.string.important_config))
                    .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_warning))
                    .setPositiveButton(R.string.dialog_si) { _, _ ->
                        super.onBackPressed()
                    }
                    .setNegativeButton(R.string.dialog_no) { _, _ -> /* no-op */ }
                dialog.create()
                dialog.show()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}
