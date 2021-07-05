package com.example.party_lojo_game.data.remote
import retrofit2.Response
import retrofit2.http.GET


interface RemoteService {

    @GET
    suspend fun getResource(): Response<ResourceDTO>
}