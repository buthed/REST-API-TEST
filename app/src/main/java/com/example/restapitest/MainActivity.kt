package com.example.restapitest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.restapitest.network.ApiClient
import com.example.restapitest.network.model.ShipX
import com.example.restapitest.ui.theme.RESTAPITESTTheme
import com.example.restapitest.view.ShipsScreen
import retrofit2.Call
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RESTAPITESTTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var data: List<ShipX> = listOf()
                    val client = ApiClient.apiService.fetchShips()
                    client.enqueue(object : retrofit2.Callback<List<ShipX>> {
                        override fun onResponse(
                            call: Call<List<ShipX>>,
                            response: Response<List<ShipX>>,
                        ) {
                            if (response.isSuccessful) {
                                data = response.body()!!
                                Log.d("GGG", response.body().toString())
                            }
                        }

                        override fun onFailure(call: Call<List<ShipX>>, t: Throwable) {
                            Log.d("GGG", "failed cause $t")
                        }

                    })
                    ShipsScreen(data)
                }
            }
        }
    }
}
