package com.example.restapitest.data.mapper

import com.example.restapitest.data.room.ShipXEntity
import com.example.restapitest.network.model.ShipX

class ShipXMapper {

    fun mapEntityToModel(shipXEntity: ShipXEntity) =
        ShipX(ship_id = shipXEntity.ship_id,
            image = shipXEntity.image)

    fun mapModelToEntity(shipX: ShipX) =
        ShipXEntity(ship_id = shipX.ship_id!!,
            image = shipX.image!!)
}