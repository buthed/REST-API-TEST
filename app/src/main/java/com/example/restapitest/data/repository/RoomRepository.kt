package com.example.restapitest.data.repository

import com.example.restapitest.data.room.ShipXDao
import com.example.restapitest.data.room.ShipXEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val shipXDao: ShipXDao
) {

    fun addShip(shipXEntity: ShipXEntity) = shipXDao.addShip(shipXEntity)

    fun getAllShips(): Flow<List<ShipXEntity>> {
        return shipXDao.getAllShips()
    }
}