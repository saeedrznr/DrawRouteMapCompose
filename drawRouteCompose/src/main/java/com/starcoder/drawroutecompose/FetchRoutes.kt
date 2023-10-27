package com.starcoder.drawroutecompose

import com.google.android.gms.maps.model.LatLng

class FetchRoutes {
    fun getRoutes(routesResponse:List<Route>):List<List<LatLng>>{
        val routes = mutableListOf<List<LatLng>>()
        routesResponse.forEach{
            val route = mutableListOf<LatLng>()
            it.legs.forEach{
                it.steps.forEach{
                    route.addAll(decodePoly(it.polyline.points))
                }
            }
            routes.add(route)
        }
        return routes
    }

    private fun decodePoly(encoded: String): List<LatLng> {
        val poly: MutableList<LatLng> = ArrayList()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }
        return poly
    }
}