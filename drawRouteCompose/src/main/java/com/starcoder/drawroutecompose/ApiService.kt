package com.starcoder.drawroutecompose

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class ApiService{

    fun getRoutes(key: String,origin: LatLng,destination: LatLng,onResponse:(List<List<LatLng>>)->Unit){
        val TAG = "drawgooglemaproute-request_response:"
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val request = Api.invoke().getRoute(
                    "${origin.latitude},${origin.longitude}",
                    "${destination.latitude},${destination.longitude}",
                    key
                )
                val data = CoroutineScope(Dispatchers.IO).async {
                    if (request.isSuccessful) {
                        return@async request.body()
                    } else {
                        return@async request.message()
                    }
                }.await()
                if (data is RouteResponse) {
                    if(data.status=="OK") {
                        onResponse(FetchRoutes().getRoutes(data.routes))
                    }
                    else {
                        Log.i(TAG,data.status)
                    }
                } else if (data is String) {
                    Log.i(TAG,data)
                }
            }catch (e:Exception){
                Log.i(TAG,e.message?:"")
            }
        }
    }
   private interface Api{
        @GET("json")
        suspend fun getRoute(@Query("origin")origin:String, @Query("destination") destination:String, @Query("key")key:String): Response<RouteResponse>

        companion object {
            private val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            operator fun invoke(): Api {
                return retrofit.create(Api::class.java)
            }
        }
    }

}



