package com.example.restapitest.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapitest.data.repository.RetrofitRepository
import com.example.restapitest.data.repository.RoomRepository
import com.example.restapitest.data.room.ShipXEntity
import com.example.restapitest.network.model.ShipX
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipsScreenViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    var ships by mutableStateOf(listOf<ShipX>())
    var networkState by mutableStateOf(false)

    val toastFromDB = Toast.makeText(context, "Data from DB", Toast.LENGTH_LONG)
    val toastFromServer = Toast.makeText(context, "Data from server", Toast.LENGTH_LONG)

    init {
        checkNetworkState()
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
                    roomRepository.addShip(ShipXEntity(ship.ship_id,ship.image))
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
                    shipsDB.add(ShipX(ship.ship_id,ship.image))
                }
                toastFromDB.show()
                ships = shipsDB.toList()
            }
        }
    }
}