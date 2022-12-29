package com.example.restapitest.network.model

import com.squareup.moshi.Json

data class ShipX(
    @Json(name = "ship_id")
    val ship_id: String?,
    @Json(name = "image")
    val image: String?
)