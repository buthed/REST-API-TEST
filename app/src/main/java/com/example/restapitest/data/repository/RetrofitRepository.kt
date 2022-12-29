package com.example.restapitest.data.repository

import com.example.restapitest.network.ApiClient
import com.example.restapitest.network.model.ShipX
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
  private val apiClient: ApiClient
) {

    suspend fun getAllShips(): List<ShipX> = apiClient.apiService.fetchShips()
}