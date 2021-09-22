package com.example.party_lojo_game.data.remote
import com.example.party_lojo_game.di.RemoteModule
import com.example.party_lojo_game.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RemoteService {

    @GET(Constants.BEBE_QUIEN_URL)
    suspend fun getBebeQuienResource(): Response<List<ResourceDTO>>

    @GET(Constants.YO_NUNCA_URL)
    suspend fun getYoNuncaResource(): Response<List<ResourceDTO>>

    @GET(Constants.VERDAD_O_RETO_URL)
    suspend fun getVerdadOretoResource(): Response<List<ResourceDTO>>
}