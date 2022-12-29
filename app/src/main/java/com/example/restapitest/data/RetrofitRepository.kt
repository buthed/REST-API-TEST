package com.example.restapitest.data

import com.example.restapitest.network.ApiClient
import com.example.restapitest.network.model.Ship
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun getAllShips(): List<Ship> = apiClient.apiService.fetchShips()
}