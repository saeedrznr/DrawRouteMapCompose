package com.starcoder.drawroutecompose

data class RouteResponse(
    val routes: List<Route>,
    val status: String
)

data class Route(
    val legs: List<Leg>,
)

data class Leg(
    val steps: List<Step>
)

data class Polyline(
    val points: String
)

data class Step(
    val polyline: Polyline
)