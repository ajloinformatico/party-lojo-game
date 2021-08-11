package com.example.party_lojo_game.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO

@Dao
interface BebeQuienDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBebeQuienAsk(bebeQuienDBO: BebeQuienDBO)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListBebeQuienAsk(bebeQuienDBO: BebeQuienDBO)

    @Query("DELETE FROM bebequien WHERE 1 = 1")
    suspend fun deleteAllBebeQuienAsk(bebeQuienDBO: BebeQuienDBO)

    @Query("DELETE FROM bebequien WHERE id = :id")
    suspend fun deleteBebeQuienAsk(id: Long)
}