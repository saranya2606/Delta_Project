package com.example.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class LodgingDetailsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LodgingDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lodging_details)
        val placeId = intent.getStringExtra("placeName")

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = LodgingDetailsAdapter(LodgingDetails("", emptyList()))
        //adapter = LodgingDetailsAdapter(emptyList()) // Pass an empty list initially
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        if (placeId != null) {
            fetchPlaceDetails(placeId)
        }
    }

    private fun fetchPlaceDetails(placeId: String) {
        val apiKey = "AIzaSyD5ekvYbjJp_KFPGgm2flRXDfEgNOt_p_o"
        val url = "https://maps.googleapis.com/maps/api/place/details/json" +
                "?placeid=$placeId&fields=name,reviews&key=$apiKey"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val lodgingName = response.getJSONObject("result").getString("name")
                val reviewsArray = response.getJSONObject("result").getJSONArray("reviews")
                val reviewsList = mutableListOf<String>()
                for (i in 0 until reviewsArray.length()) {
                    val review = reviewsArray.getJSONObject(i).getString("text")
                    reviewsList.add(review)
                }
                val lodgingDetails = LodgingDetails(lodgingName, reviewsList)
                adapter.updateData(lodgingDetails)
            },
            { error ->
                // Handle API request error
            })

        Volley.newRequestQueue(this).add(request)
    }
}

