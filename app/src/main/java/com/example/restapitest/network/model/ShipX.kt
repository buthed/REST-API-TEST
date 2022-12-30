package com.example.restapitest.network.model

import com.squareup.moshi.Json

data class ShipX(
    @Json(name = "ship_id")
    val ship_id: String,
    @Json(name = "image")
    val image: String?,
    @Json(name = "ship_name")
    val ship_name: String?,
    @Json(name = "home_port")
    val home_port: String?,
    @Json(name = "url")
    val url: String?,
)