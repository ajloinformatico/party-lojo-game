package com.example.party_lojo_game.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.party_lojo_game.R
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.*

/**Random number from (inclusive) to (inclusive)*/
fun rand(from: Int, to: Int) : Int {
    return Random().nextInt(to+1 - from) + from
}

fun createImageList(): List<String>{
    val list: MutableList<String> = mutableListOf()
    for (i in 1..47) {
        list.add("user$i.png")
    }
    return list
}

//TODO: MOVE TO VIEWMODEL
fun getImage(resource: String, requireContext: Context): Drawable? {
    return when (resource) {
        "user1.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user1)

        "user2.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user2)

        "user3.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user3)

        "user4.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user4)

        "user5.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user5)

        "user6.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user6)

        "user7.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user7)

        "user8.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user8)

        "user9.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user9)

        "user10.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user10)

        "user11.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user11)

        "user12.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user12)

        "user13.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user13)

        "user14.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user14)

        "user15.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user15)

        "user16.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user16)

        "user17.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user17)

        "user18.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user18)

        "user19.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user19)

        "user20.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user20)

        "user21.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user21)

        "user22.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user22)

        "user23.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user23)

        "user24.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user24)

        "user25.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user25)

        "user26.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user27)

        "user28.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user28)

        "user29.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user29)

        "user30.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user30)

        "user31.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user31)

        "user32.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user32)

        "user33.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user33)

        "user34.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user34)

        "user35.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user35)

        "user36.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user36)

        "user37.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user37)

        "user38.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user38)

        "user39.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user39)

        "user40.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user40)

        "user41.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user41)

        "user42.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user42)

        "user43.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user43)

        "user44.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user44)

        "user45.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user45)

        "user46.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user46)

        "user47.png" -> AppCompatResources.getDrawable(requireContext, R.mipmap.user47)

        else -> AppCompatResources.getDrawable(requireContext, R.mipmap.user1)
    }
}