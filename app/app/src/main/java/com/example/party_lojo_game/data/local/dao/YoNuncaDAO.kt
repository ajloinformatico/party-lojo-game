package com.example.party_lojo_game.data.local.dao

import androidx.room.*
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface YoNuncaDAO {

    @Transaction
    @Query("SELECT * FROM yonunca")
    fun selectAllFromYoNunca(): Flow<List<YoNuncaDBO>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertYoNuncaAsk(yoNuncaDBO: YoNuncaDBO): Long

    @Query("DELETE FROM yonunca WHERE id = :id")
    suspend fun deleteYoNuncaAsk(id: Long)
}