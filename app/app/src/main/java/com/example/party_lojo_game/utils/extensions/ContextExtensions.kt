package com.example.party_lojo_game.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.party_lojo_game.R


fun Context.findUserResource(resource: String): Drawable? =
    ContextCompat.getDrawable(
        this,
        when (resource) {
            "user1.png" -> R.mipmap.user1

            "user2.png" -> R.mipmap.user2

            "user3.png" -> R.mipmap.user3

            "user4.png" -> R.mipmap.user4

            "user5.png" -> R.mipmap.user5

            "user6.png" -> R.mipmap.user6

            "user7.png" -> R.mipmap.user7

            "user8.png" -> R.mipmap.user8

            "user9.png" -> R.mipmap.user9

            "user10.png" -> R.mipmap.user10

            "user11.png" -> R.mipmap.user11

            "user12.png" -> R.mipmap.user12

            "user13.png" -> R.mipmap.user13

            "user14.png" -> R.mipmap.user14

            "user15.png" -> R.mipmap.user15

            "user16.png" -> R.mipmap.user16

            "user17.png" -> R.mipmap.user17

            "user18.png" -> R.mipmap.user18

            "user19.png" -> R.mipmap.user19

            "user20.png" -> R.mipmap.user20

            "user21.png" -> R.mipmap.user21

            "user22.png" -> R.mipmap.user22

            "user23.png" -> R.mipmap.user23

            "user24.png" -> R.mipmap.user24

            "user25.png" -> R.mipmap.user25

            "user26.png" -> R.mipmap.user26

            "user28.png" -> R.mipmap.user28

            "user29.png" -> R.mipmap.user29

            "user30.png" -> R.mipmap.user30

            "user31.png" -> R.mipmap.user31

            "user32.png" -> R.mipmap.user32

            "user33.png" -> R.mipmap.user33

            "user34.png" -> R.mipmap.user34

            "user35.png" -> R.mipmap.user35

            "user36.png" -> R.mipmap.user36

            "user37.png" -> R.mipmap.user37

            "user38.png" -> R.mipmap.user38

            "user39.png" -> R.mipmap.user39

            "user40.png" -> R.mipmap.user40

            "user41.png" -> R.mipmap.user41

            "user42.png" -> R.mipmap.user42

            "user43.png" -> R.mipmap.user43

            "user44.png" -> R.mipmap.user44

            "user45.png" -> R.mipmap.user45

            "user46.png" -> R.mipmap.user46

            "user47.png" -> R.mipmap.user47

            else -> R.mipmap.user1
        }
    )
