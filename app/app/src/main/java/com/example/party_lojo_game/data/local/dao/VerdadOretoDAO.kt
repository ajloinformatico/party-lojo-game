package com.example.party_lojo_game.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface VerdadOretoDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertVerdadOretoAsk(verdadOretoDBO: VerdadOretoDBO)

    @Query("DELETE FROM verdadoreto WHERE id = :id")
    suspend fun deleteVerdadOretoAsk(id: Long)

}