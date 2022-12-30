package com.example.restapitest.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.restapitest.network.model.ShipX
import com.example.restapitest.ui.theme.Typography

@Composable
fun ShipsScreen() {
    val viewModel = hiltViewModel<ShipsScreenViewModel>()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(text = viewModel.title)
        if (viewModel.ships.isNotEmpty()) {
            LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(viewModel.ships){ ship->
                    Ship(ship)
                }
            }
        }
    }
    AnimatedVisibility(visible = viewModel.shipOnFullScreen) {
        ShipScreen(viewModel)
    }
}

@Composable
fun Ship(ship: ShipX) {
    val viewModel = hiltViewModel<ShipsScreenViewModel>()

    Text(text = ship.ship_name ?: "", style = Typography.body1)
    AsyncImage(
        model = ship.image,
        contentDescription = null,
        Modifier.clickable {
            viewModel.currentShip = ship
            viewModel.shipOnFullScreen = true
        }
    )
}