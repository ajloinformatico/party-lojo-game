package com.example.party_lojo_game.data.repository

import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val bebeQuienDAO: BebeQuienDAO,
    private val verdadOretoDAO: VerdadOretoDAO,
    private  val yoNuncaDAO: YoNuncaDAO
    ){
}