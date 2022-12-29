package com.example.restapitest.view

import android.util.Log
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
import com.example.restapitest.ui.theme.Typography

@Composable
fun ShipsScreen() {
    val viewModel = hiltViewModel<ShipsScreenViewModel>()
    Log.d("GGG", "open ship screen")

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(text = "Ships List")
        if (viewModel.ships.isNotEmpty()) {
            LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(viewModel.ships){ ship->
                    if (ship.ship_id!=null && ship.image!=null) {
                        Ship(ship.ship_id, ship.image)
                    }
                }
            }
        }
    }
}

@Composable
fun Ship(id: String, picture: String) {
    Text(text = id, style = Typography.body1)
    AsyncImage(
        model = picture,
        contentDescription = null
    )
}