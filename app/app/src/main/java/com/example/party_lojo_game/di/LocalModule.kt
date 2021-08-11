package com.example.party_lojo_game.di

import android.content.Context
import androidx.room.Room
import com.example.party_lojo_game.data.local.PartyLojoGameDataBase
import com.example.party_lojo_game.data.local.dao.BebeQuienDAO
import com.example.party_lojo_game.data.local.dao.VerdadOretoDAO
import com.example.party_lojo_game.data.local.dao.YoNuncaDAO
import com.example.party_lojo_game.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    fun localDataBaseProvider(@ApplicationContext appContext: Context): PartyLojoGameDataBase =
        Room.databaseBuilder(
            appContext,
            PartyLojoGameDataBase::class.java,
            Constants.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideBebeQuienDAO(
        database: PartyLojoGameDataBase
    ): BebeQuienDAO = database.bebeQuienDAO()

    @Provides
    @Singleton
    fun provideVerdadOretoDAO(
        database: PartyLojoGameDataBase
    ): VerdadOretoDAO = database.verdadOretoDAO()

    @Provides
    @Singleton
    fun provideYoNuncaDAO(
        database: PartyLojoGameDataBase
    ): YoNuncaDAO = database.yoNuncaDAO()
}