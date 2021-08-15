package com.example.party_lojo_game.data.remote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RemoteService {

    //TODO dont know if this is going to run
    @GET("{url}")
    suspend fun getResource(@Path("url") url: String): Response<List<ResourceDTO>>
}