package com.starcoder.drawroutecompose.model

data class RouteResponse(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)