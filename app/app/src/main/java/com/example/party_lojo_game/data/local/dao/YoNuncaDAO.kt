package com.example.party_lojo_game.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO

@Dao
interface YoNuncaDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertYoNuncaAsk(yoNuncaDBO: YoNuncaDBO)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListYoNuncaAsk(yoNuncaDBO: YoNuncaDBO)

    @Query("DELETE FROM yonunca WHERE 1 = 1")
    suspend fun deleteAllYoNuncaAsk(yoNuncaDBO: YoNuncaDBO)

    @Query("DELETE FROM yonunca WHERE id = :id")
    suspend fun deleteYoNuncaAsk(id: Long)
}