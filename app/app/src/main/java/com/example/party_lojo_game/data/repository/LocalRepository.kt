package com.example.party_lojo_game.data.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.annotation.WorkerThread
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.ui.base.RepositoryBase
import com.example.party_lojo_game.utils.className
import com.example.party_lojo_game.utils.logger.InfoLojoLogger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val bebeQuienDAO: BebeQuienDAO,
    private val verdadOretoDAO: VerdadOretoDAO,
    private val yoNuncaDAO: YoNuncaDAO
) : RepositoryBase() {
    // region selects
    val selectAllFromBebeQuien: Flow<List<BebeQuienDBO>> = bebeQuienDAO.selectAllFromBebeQuien()

    val selectAllFromVerdadOreto: Flow<List<VerdadOretoDBO>> =
        verdadOretoDAO.selectAllFromVerdadOreto()

    val selectAllFromYoNunca: Flow<List<YoNuncaDBO>> = yoNuncaDAO.selectAllFromYoNunca()
    // endregion selects

    // region inserts
    @WorkerThread
    suspend fun insertBebeQuienAsk(askBO: AsksBO): Long? = try {
        bebeQuienDAO.insertBebeQuienAsk(BebeQuienDBO(0, askBO.text))
    } catch (e: SQLiteConstraintException) {
        InfoLojoLogger.log("Error: $askBO is already in database", className())
        null
    }

    @WorkerThread
    suspend fun insertYoNuncaAsk(askBO: AsksBO): Long? = try {
        yoNuncaDAO.insertYoNuncaAsk(YoNuncaDBO(0, askBO.text))
    } catch (e: SQLiteConstraintException) {
        InfoLojoLogger.log("Error: $askBO is already in database", className())
        null
    }

    @WorkerThread
    suspend fun insertVerdadOretoAsk(askBO: AsksBO): Long? = try {
        verdadOretoDAO.insertVerdadOretoAsk(VerdadOretoDBO(0, askBO.text))
    } catch (e: SQLiteConstraintException) {
        InfoLojoLogger.log("Error: $askBO is already in database", className())
        null
    }
    // endregion inserts
}
