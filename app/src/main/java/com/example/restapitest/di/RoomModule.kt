package com.example.restapitest.di

import android.content.Context
import androidx.room.Room
import com.example.restapitest.data.room.ShipXDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun shipXDao(shipXDB: ShipXDB) = shipXDB.shipXDao()

    @Provides
    @Singleton
    fun shipXDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ShipXDB::class.java,
        "ShipXDB"
    ).build()
}