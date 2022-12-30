package com.example.restapitest.view

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapitest.MainActivity
import com.example.restapitest.R
import com.example.restapitest.data.mapper.ShipXMapper
import com.example.restapitest.data.repository.RetrofitRepository
import com.example.restapitest.data.repository.RoomRepository
import com.example.restapitest.network.model.ShipX
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipsScreenViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository,
    @ApplicationContext private val context: Context,
    private val activity: Activity = MainActivity()
): ViewModel() {

    var ships by mutableStateOf(listOf<ShipX>())
    var networkState by mutableStateOf(false)
    var title by mutableStateOf("Ships List")

    var shipOnFullScreen by mutableStateOf(false)
    var currentShip by mutableStateOf(ShipX("AMERICANCHAMPION","https://i.imgur.com/woCxpkj.jpg","American Champion", "Port of Los Angeles", "https://www.marinetraffic.com/en/ais/details/ships/shipid:434663/vessel:AMERICAN%20CHAMPION"))

    val toastFromDB = Toast.makeText(context, "Data from DB", Toast.LENGTH_LONG)
    val toastFromServer = Toast.makeText(context, "Data from server", Toast.LENGTH_LONG)

    init {
        checkNetworkState()
        remoteConfig()
    }

    private fun checkNetworkState() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw= cm.activeNetwork
        val netInfo = cm.getNetworkCapabilities(nw)
        if (netInfo!=null) {
            networkState = when {
                netInfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                netInfo.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        getShips(networkState)
    }

    fun getShips(networkState: Boolean) {
        if (networkState) getAllShipsFromNetwork()
        else getAllShipsFromDB()
    }

    fun getAllShipsFromNetwork() {
        viewModelScope.launch {
            ships = retrofitRepository.getAllShips()
            retrofitRepository.getAllShips().forEach { ship->
                if (ship.ship_id!=null && ship.image!=null) {
                    roomRepository.addShip(ShipXMapper().mapModelToEntity(ship))
                }
            }
            toastFromServer.show()
        }
    }

    fun getAllShipsFromDB() {
        viewModelScope.launch {
            val shipsDB = arrayListOf<ShipX>()
            roomRepository.getAllShips().collect {
                it.forEach { ship->
                    shipsDB.add(ShipXMapper().mapEntityToModel(ship))
                }
                toastFromDB.show()
                ships = shipsDB.toList()
            }
        }
    }

    fun remoteConfig() {
        val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder().build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.default_values)
        remoteConfig.fetch(2)
            .addOnCompleteListener(activity) { task->
                if (task.isSuccessful) {
                    remoteConfig.activate()
                    title = remoteConfig.getString("api_title")
                    Log.d("GGG", "title is ${remoteConfig.getString("api_title")}")
                }
            }
    }
}