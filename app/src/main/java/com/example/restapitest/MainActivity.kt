package com.example.restapitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.restapitest.ui.theme.RESTAPITESTTheme
import com.example.restapitest.view.ShipsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RESTAPITESTTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ShipsScreen()
                }
            }
        }
    }
}