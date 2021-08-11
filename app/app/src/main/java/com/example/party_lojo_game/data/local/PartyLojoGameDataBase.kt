package com.example.party_lojo_game.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO

@Database(entities = [BebeQuienDBO::class, VerdadOretoDBO::class, YoNuncaDBO::class], version = 1, exportSchema = false)
abstract class PartyLojoGameDataBase: RoomDatabase() {
    abstract fun bebeQuienDAO(): BebeQuienDAO
    abstract fun verdadOretoDAO(): VerdadOretoDAO
    abstract fun yoNuncaDAO(): YoNuncaDAO
}
