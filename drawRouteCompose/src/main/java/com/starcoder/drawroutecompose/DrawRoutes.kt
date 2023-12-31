package com.starcoder.drawroutecompose


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.ButtCap
import com.google.android.gms.maps.model.Cap
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PatternItem
import com.google.android.gms.maps.model.Polyline
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun DrawRoutes(
    key: String, origin: LatLng, destination: LatLng,
    clickable: Boolean = false,
    colors: List<Color>? = null,
    endCap: Cap = ButtCap(),
    geodesic: Boolean = false,
    jointType: Int = JointType.DEFAULT,
    pattern: List<PatternItem>? = null,
    startCap: Cap = ButtCap(),
    visible: Boolean = true,
    width: Float = 10f,
    zIndex: Float = 0f,
    onClick: (Polyline) -> Unit = {},
    originMarker: Boolean = false,
    destinationMarker: Boolean = false,
    originTitle: String = "",
    destinationTitle: String = "",
    originSnippet: String? = null,
    destinationSnippet: String? = null,
    alternatives:Boolean=false
) {
    val routesColors = colors ?: listOf(Color.Blue, Color.Red, Color.Green, Color.Magenta)
    val apiService: ApiService = viewModel()
    val routes by apiService.routes.observeAsState()
    apiService.getRoutes(key, origin, destination,alternatives)
    val desMarkerState = rememberMarkerState(position = destination)
    val begMarkerState = rememberMarkerState(position = origin)
    desMarkerState.showInfoWindow()
    begMarkerState.showInfoWindow()
    if (originMarker) {
        Marker(
            state = begMarkerState,
            title = originTitle,
            onClick = {
                begMarkerState.showInfoWindow()
                true
            },
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
            snippet = originSnippet
        )
    }
    if (destinationMarker) {
        Marker(
            state = desMarkerState,
            title = destinationTitle,
            onClick = {
                desMarkerState.showInfoWindow()
                true
            },
            snippet = destinationSnippet,
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        )
    }

    if (routes != null) {
        routes!!.indices.forEach {
            Polyline(
                points = routes!![it],
                color = routesColors[it % routesColors.size],
                clickable = clickable, endCap = endCap,
                geodesic = geodesic,
                jointType = jointType,
                pattern = pattern,
                startCap = startCap,
                visible = visible,
                width = width,
                zIndex = zIndex,
                onClick = onClick
            )
        }
    }

}

