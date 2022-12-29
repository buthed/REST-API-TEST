package com.example.restapitest.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapitest.data.RetrofitRepository
import com.example.restapitest.network.model.ShipX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipsScreenViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository
): ViewModel() {

    var ships by mutableStateOf(listOf<ShipX>())

    init {
        getAllShips()
    }

    fun getAllShips() {
        viewModelScope.launch {
            ships = retrofitRepository.getAllShips()

        }
    }
}