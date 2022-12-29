package com.example.restapitest.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ships")
data class ShipXEntity(
    @PrimaryKey
    val ship_id: String,
    val image: String
)