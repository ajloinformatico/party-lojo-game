package com.example.party_lojo_game.di

import com.example.party_lojo_game.data.remote.RemoteService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    const val BASE_URL = "https://gist.githubusercontent.com/ajloinformatico/"


    @Singleton
    @Provides fun provideRemoteData(): RemoteService {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        return retrofit.create(RemoteService::class.java)
    }
}