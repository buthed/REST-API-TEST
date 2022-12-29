package com.example.restapitest.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun ShipsScreen() {
    val viewModel = hiltViewModel<ShipsScreenViewModel>()
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(text = "Ships List")
        if (viewModel.ships!=null) {
            LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(viewModel.ships) { ship->
                    if (ship.ship_id!=null && ship.image!=null) Ship(ship.ship_id, ship.image)
                }
            }
        }
    }
}

@Composable
fun Ship(id: String, picture: String) {
    Text(text = id,)
    AsyncImage(
        model = picture,
        contentDescription = null
    )
}