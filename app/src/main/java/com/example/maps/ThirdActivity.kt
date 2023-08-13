package com.example.maps

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ThirdActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val placeName2:String
        val placesList= mutableListOf<Place>()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //val more=findViewById<Button>(R.id.more)

        /*more.setOnClickListener{
            val intent = Intent(this, FourthActivity::class.java)
            intent.putParcelableArrayListExtra("places", ArrayList(placesList))
            startActivity(intent)
        }*/
        //mapView = mapFragment.view as MapView
        //mapView.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val defaultLocation = LatLng(19.16, 73.34)

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        googleMap=map
        googleMap.setOnInfoWindowClickListener { marker ->
            val intent = Intent(this@ThirdActivity, LodgingDetailsActivity::class.java)
            intent.putExtra("placeName", marker.title)
            //intent.putExtra("placeNamw", marker.title)
            startActivity(intent)
        }
        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                // Return null here to use the default info window
                return null
            }

            override fun getInfoContents(marker: Marker): View {
                val infoView = LayoutInflater.from(this@ThirdActivity)
                    .inflate(R.layout.info_window_layout, null) // Create your custom info window layout

                val titleTextView = infoView.findViewById<TextView>(R.id.titleTextView)
                val snippetTextView = infoView.findViewById<TextView>(R.id.snippetTextView)

                titleTextView.text = marker.title
                snippetTextView.text = marker.snippet

                return infoView
            }
        })
        val searchButton = findViewById<Button>(R.id.search)
        val search=findViewById<EditText>(R.id.searchet)
        searchButton.setOnClickListener {
            val locationQuery = search.text
            searchLocation(locationQuery,googleMap)
        }



        googleMap.setOnMapClickListener { latLng ->

            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(latLng))


            val latitude = latLng.latitude
            val longitude = latLng.longitude

            val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val placesApiService = retrofit.create(PlacesApiService::class.java)

            val location = "$latitude,$longitude"
            val radius = 5000
            val type = "lodging"
            val apiKey = "AIzaSyD5ekvYbjJp_KFPGgm2flRXDfEgNOt_p_o"

            val call: Call<ApiResponse> = placesApiService.getNearbyHotels(location, radius, type, apiKey)

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    if (response.isSuccessful) {
                        val placesList= mutableListOf<Place>()
                        val places = response.body()?.results
                        if (places != null) {
                            for (place in places) {
                                val placeName = place.name
                                val placeLatLng = place.geometry.location

                                Log.d("Check","$places")
                                val markerOptions = MarkerOptions()
                                    .position(LatLng(placeLatLng.lat, placeLatLng.lng))
                                    .title(placeName)

                                placesList.add(place)
                                    //.tag = place.placeId

                                googleMap.addMarker(markerOptions)
                                val marker = googleMap.addMarker(markerOptions)
                                if (marker != null) {
                                    marker.tag = placeName
                                }
                            }
                        }
                    } else {

                        val errorCode = response.code()
                        val errorBody = response.errorBody()?.string()

                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

                }
            })


        }
    }





}
private fun searchLocation(query: Editable,googleMap: GoogleMap){


        val apiKey = "AIzaSyD5ekvYbjJp_KFPGgm2flRXDfEgNOt_p_o"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val geocodingApiService = retrofit.create(PlacesApiService::class.java)

        val call: Call<GeoResponse> = geocodingApiService.geoAddress(query, apiKey)

        call.enqueue(object : Callback<GeoResponse> {
            override fun onResponse(call: Call<GeoResponse>, response: Response<GeoResponse>) {
                if (response.isSuccessful) {
                    val results = response.body()?.results
                    if (results != null && results.isNotEmpty()) {
                        val location = results[0].geometry.location

                        fetchNearbyHotels(googleMap,location.lat, location.lng)
                    } else {

                    }
                } else {
                    // Handle API request error
                    val errorCode = response.code()
                    val errorBody = response.errorBody()?.string()
                    // Handle the error accordingly
                }
            }

            override fun onFailure(call: Call<GeoResponse>, t: Throwable) {

            }
        })
    }
private fun fetchNearbyHotels(googleMap: GoogleMap,latitude: Double, longitude: Double) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val placesApiService = retrofit.create(PlacesApiService::class.java)

    val location = "$latitude,$longitude"
    val radius = 5000
    val type = "lodging"
    val apiKey = "AIzaSyD5ekvYbjJp_KFPGgm2flRXDfEgNOt_p_o"

    val call: Call<ApiResponse> = placesApiService.getNearbyHotels(location, radius, type, apiKey)

    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val places = response.body()?.results
                if (places != null) {
                    for (place in places) {
                        val placeName = place.name
                        val placeLatLng = place.geometry.location

                        val markerOptions = MarkerOptions()
                            .position(LatLng(placeLatLng.lat, placeLatLng.lng))
                            .title(placeName)

                        googleMap.addMarker(markerOptions)
                    }
                }
            } else {

                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()

            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

        }
    })
}



