package com.example.restapitest.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShipsScreen() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(text = "Ships List")
        LazyColumn() {
            items(3) {
                Ship("SHIP", "PIC")
            }
        }
    }
}

@Composable
fun Ship(id: String, picture: String) {
    Text(text = id,)
    Text(text = picture)
}