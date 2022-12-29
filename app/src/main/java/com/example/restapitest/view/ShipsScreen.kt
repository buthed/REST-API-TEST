package com.example.restapitest.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restapitest.network.model.ShipX

@Composable
fun ShipsScreen(data: List<ShipX>) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(text = "Ships List")
        LazyColumn() {
            items(data) { ship->
                Ship(ship.ship_id.toString(), ship.image.toString())
            }
        }
    }
}

@Composable
fun Ship(id: String, picture: String) {
    Text(text = id,)
    Text(text = picture)
}