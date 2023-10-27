package com.starcoder.drawrouteonmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.starcoder.drawroutecompose.DrawRoutes
import com.starcoder.drawrouteonmap.ui.theme.DrawRouteOnMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawRouteOnMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   GoogleMap(modifier=Modifier.fillMaxSize()) {
                       DrawRoutes(
                           key = "API_KEY",
                           origin = LatLng(23.173093, -102.868270),
                           destination = LatLng(60.005543, -111.884948),
                           originMarker = true,
                           destinationMarker = true
                       )
                   }
                }
            }
        }
    }
}

