package com.example.party_lojo_game.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Singleton to save data in memory
 */
@InstallIn(SingletonComponent::class)
@Module
object PartyLojoGameProvider
