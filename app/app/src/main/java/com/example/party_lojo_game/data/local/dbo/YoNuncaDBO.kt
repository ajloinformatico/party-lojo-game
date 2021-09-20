package com.example.party_lojo_game.data.local.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.party_lojo_game.data.AskTypeBO
import com.example.party_lojo_game.data.AsksBO

@Entity(tableName = "yonunca")
data class YoNuncaDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)

fun YoNuncaDBO.toBO(): AsksBO =
    AsksBO(
        id = this.id,
        text = this.text,
        type = AskTypeBO.YO_NUNCA
    )