package com.example.restapitest.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShipXDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addShip(shipXEntity: ShipXEntity)

    @Query("SELECT * FROM ships")
    fun getAllShips(): Flow<List<ShipXEntity>>
}