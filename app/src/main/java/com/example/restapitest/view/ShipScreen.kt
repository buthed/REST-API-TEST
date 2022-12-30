package com.example.restapitest.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ShipScreen(viewModel: ShipsScreenViewModel) {
    Column(Modifier.clickable { viewModel.shipOnFullScreen = false }.background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = viewModel.currentShip.image, contentDescription = "",
            Modifier.size(220.dp))
        Column(Modifier.padding(horizontal = 30.dp)) {
            Row() {
                Text(text = "name: ")
                Text(text = viewModel.currentShip.ship_id)
            }
            Row() {
                Text(text = "home_port: ")
                Text(text = viewModel.currentShip.home_port ?: "")
            }
            Row() {
                Text(text = "link: ")
                Text(text = viewModel.currentShip.url ?: "")
            }
        }
    }
}