package com.starcoder.drawroutecompose.model

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)