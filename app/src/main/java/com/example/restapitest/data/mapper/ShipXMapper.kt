package com.example.restapitest.data.mapper

import com.example.restapitest.data.room.ShipXEntity
import com.example.restapitest.network.model.ShipX

class ShipXMapper {

    fun mapEntityToModel(shipXEntity: ShipXEntity) =
        ShipX(ship_id = shipXEntity.ship_id,
            image = shipXEntity.image,
            ship_name = shipXEntity.ship_name,
            home_port = shipXEntity.home_port,
            url = shipXEntity.url)

    fun mapModelToEntity(shipX: ShipX) =
        ShipXEntity(ship_id = shipX.ship_id!!,
            image = shipX.image!!,
            ship_name = shipX.ship_name!!,
            home_port = shipX.home_port!!,
            url = shipX.url)

    fun mapListEntityToListModel(list: List<ShipXEntity>) = list.map {
        mapEntityToModel(it)
    }

    fun mapListModelToListEntity(list: List<ShipX>) = list.map {
        mapModelToEntity(it)
    }
}