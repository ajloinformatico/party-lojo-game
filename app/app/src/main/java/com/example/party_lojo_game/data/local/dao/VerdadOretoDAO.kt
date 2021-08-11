package com.example.party_lojo_game.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO

@Dao
interface VerdadOretoDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertVerdadOretoAsk(verdadOretoDBO: VerdadOretoDBO)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListVerdadOretoAsk(verdadOretoDBO: VerdadOretoDBO)

    @Query("DELETE FROM verdadoreto WHERE 1 = 1")
    suspend fun deleteAllVerdadOretoAsk(verdadOretoDBO: VerdadOretoDBO)

    @Query("DELETE FROM verdadoreto WHERE id = :id")
    suspend fun deleteVerdadOretoAsk(id: Long)

}