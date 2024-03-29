package com.example.party_lojo_game.data.local.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

@Entity(tableName = "bebequien", indices = [Index(value = ["text"], unique = true)])
data class BebeQuienDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)

fun BebeQuienDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.BEBE_QUIEN
    )
