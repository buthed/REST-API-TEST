package com.example.restapitest.network.model

import com.squareup.moshi.Json

data class Data(
    @Json(name = "ships")
    val ships: List<ShipX>
)