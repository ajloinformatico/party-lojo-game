package com.example.party_lojo_game.data.repository

import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.data.remote.RemoteService
import com.example.party_lojo_game.data.remote.ResourceDTO
import com.example.party_lojo_game.data.remote.toBO
import com.example.party_lojo_game.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteService: RemoteService) {

    /** Make response from api service and return response converted to bo objects list*/
    suspend fun getAllAsksResponse(type: String): List<AsksBO> {
        val returnList = mutableListOf<AsksBO>()

        val resourceData: Response<List<ResourceDTO>>? = when (type) {
            Constants.YO_NUNCA_URL -> {
                remoteService.getYoNuncaResource()
            }
            Constants.BEBE_QUIEN_URL -> {
                remoteService.getBebeQuienResource()
            }
            Constants.VERDAD_O_RETO_URL -> {
                remoteService.getVerdadOretoResource()
            }
            else -> null
        }

        resourceData?.let { response ->
            if (response.isSuccessful) {
                response.body()?.forEach {
                    returnList.add(it.toBO())
                }
            }
        }

        return returnList
    }

}
