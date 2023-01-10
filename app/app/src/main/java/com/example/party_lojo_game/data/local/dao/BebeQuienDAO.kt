package com.example.party_lojo_game.data.local.dao

import androidx.room.*
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface BebeQuienDAO {

    @Transaction
    @Query("SELECT * FROM bebequien")
    fun selectAllFromBebeQuien(): Flow<List<BebeQuienDBO>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBebeQuienAsk(bebeQuienDBO: BebeQuienDBO): Long

    @Query("DELETE FROM bebequien WHERE id = :id")
    suspend fun deleteBebeQuienAsk(id: Long)
}
