package com.starcoder.drawroutecompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


class ApiService : ViewModel() {
    private val _routes = MutableLiveData<List<List<LatLng>>>()
    val routes: LiveData<List<List<LatLng>>>
        get() = _routes

    fun getRoutes(key: String, origin: LatLng, destination: LatLng,alternatives: Boolean) {
        val TAG = "drawgooglemaproute-request_response:"
        viewModelScope.launch {
            try {
                val request = Api.invoke().getRoute(
                    "${origin.latitude},${origin.longitude}",
                    "${destination.latitude},${destination.longitude}",
                    key,alternatives
                )
                val data = CoroutineScope(Dispatchers.IO).async {
                    if (request.isSuccessful) {
                        return@async request.body()
                    } else {
                        return@async request.message()
                    }
                }.await()

                if (data is ResponseBody) {
                    val json = JsonParser().parse(data.string()).asJsonObject
                    if (json.get("status").asString == "OK") {
                        CoroutineScope(Dispatchers.IO).launch {
                            _routes.postValue(FetchRoutes().getRoutes(json.getAsJsonArray("routes")))
                        }
                    } else {
                        Log.i(TAG, json.get("status").asString)
                    }
                } else if (data is String) {
                    Log.i(TAG, data)
                }
            } catch (e: Exception) {
                Log.i(TAG, e.message ?: "")
            }
        }
    }

    private interface Api {
        @GET("json")
        suspend fun getRoute(
            @Query("origin") origin: String,
            @Query("destination") destination: String,
            @Query("key") key: String,
            @Query("alternatives") alternatives:Boolean
        ): Response<ResponseBody>

        companion object {
            private val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                    .build()
            }

            operator fun invoke(): Api {
                return retrofit.create(Api::class.java)
            }
        }
    }

}



