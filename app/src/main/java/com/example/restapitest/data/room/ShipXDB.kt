package com.example.restapitest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShipXEntity::class], version = 1, exportSchema = false)
abstract class ShipXDB: RoomDatabase() {

    abstract fun shipXDao(): ShipXDao
}