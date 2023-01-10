package com.example.party_lojo_game.data.repository

import androidx.lifecycle.asLiveData
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.data.local.dbo.BebeQuienDBO
import com.example.party_lojo_game.data.local.dbo.VerdadOretoDBO
import com.example.party_lojo_game.data.local.dbo.YoNuncaDBO
import com.example.party_lojo_game.data.local.dbo.toBO
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val bebeQuienDAO: BebeQuienDAO,
    private val verdadOretoDAO: VerdadOretoDAO,
    private val yoNuncaDAO: YoNuncaDAO
) {
    val selectAllFromBebeQuien: List<AsksBO> =
        bebeQuienDAO.selectAllFromBebeQuien().asLiveData().value?.map { it.toBO() } ?: emptyList()
    val selectAllFromVerdadOreto: List<AsksBO> =
        verdadOretoDAO.selectAllFromVerdadOreto().asLiveData().value?.map { it.toBO() }
            ?: emptyList()
    val selectAllFromYoNunca: List<AsksBO> =
        yoNuncaDAO.selectAllFromYoNunca().asLiveData().value?.map { it.toBO() } ?: emptyList()

    suspend fun insertBebeQuienAsk(askBO: AsksBO): Long =
        bebeQuienDAO.insertBebeQuienAsk(BebeQuienDBO(0, askBO.text))

    suspend fun insertYoNuncaAsk(askBO: AsksBO): Long =
        yoNuncaDAO.insertYoNuncaAsk(YoNuncaDBO(0, askBO.text))

    suspend fun insertVerdadOretoAsk(askBO: AsksBO): Long =
        verdadOretoDAO.insertVerdadOretoAsk(VerdadOretoDBO(0, askBO.text))

}