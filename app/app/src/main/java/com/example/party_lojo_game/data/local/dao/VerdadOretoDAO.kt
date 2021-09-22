package com.example.party_lojo_game.data.local.dao

import androidx.room.*
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface VerdadOretoDAO {

    @Transaction
    @Query("SELECT * FROM verdadoreto")
    fun selectAllFromVerdadOreto(): Flow<List<VerdadOretoDBO>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertVerdadOretoAsk(verdadOretoDBO: VerdadOretoDBO)

    @Query("DELETE FROM verdadoreto WHERE id = :id")
    suspend fun deleteVerdadOretoAsk(id: Long)

}