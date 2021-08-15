package com.example.party_lojo_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.party_lojo_game.databinding.ActivityMainBinding
import com.example.party_lojo_game.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getRemoteResponse()
    }


    //TODO INSTANCE VIEWMODEL AND GET AND SAVE INFO FROM APISERVICE INTO DATABASE
    //TODO IT MUST EXECUTE WHEN APP RUNS AND CHECK IF CONTENT IS THE SAME THAT DATABASE
    //TODO NAV AND LAYOUTS
}