package com.example.party_lojo_game.data.repository

import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.ui.base.RepositoryBase
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
    suspend fun insertBebeQuienAsk(askBO: AsksBO): Long =
        bebeQuienDAO.insertBebeQuienAsk(BebeQuienDBO(0, askBO.text))

    suspend fun insertYoNuncaAsk(askBO: AsksBO): Long =
        yoNuncaDAO.insertYoNuncaAsk(YoNuncaDBO(0, askBO.text))

    suspend fun insertVerdadOretoAsk(askBO: AsksBO): Long =
        verdadOretoDAO.insertVerdadOretoAsk(VerdadOretoDBO(0, askBO.text))
    // endregion inserts
}