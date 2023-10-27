package com.starcoder.drawrouteonmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
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
                    val origin = LatLng(23.173093, -102.868270)
                    val destination = LatLng(29.943059, -90.095501)
                    val cameraPosition = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(
                            LatLng(
                                (origin.latitude + destination.latitude) / 2,
                                (origin.longitude + destination.longitude) / 2
                            ), 4f
                        )
                    }
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPosition,
                        uiSettings = MapUiSettings(zoomControlsEnabled = false)
                    ) {
                        DrawRoutes(
                            key = "API_KEY",
                            origin = origin,
                            destination = destination,
                            originMarker = true,
                            destinationMarker = true
                        )
                    }
                }
            }
        }
    }
}

