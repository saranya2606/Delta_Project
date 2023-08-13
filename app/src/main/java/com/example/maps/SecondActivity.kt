package com.example.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var categoriesAdapter:adapter
    private lateinit var searchButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recyclerView1=findViewById(R.id.recycler2)
        recyclerView2=findViewById(R.id.recycler1)
        searchButton=findViewById(R.id.searchmain)
        val searchBar=findViewById<EditText>(R.id.editmain)

        /*val c1=categories("Deserts",R.drawable.desert)
        val c2=categories("Mountains",R.drawable.mountains)
        val c3=categories("Islands",R.drawable.islands)
        val c4=categories("Cities",R.drawable.city)
        val l= listOf(c1,c2,c3,c4)

        val p1=popular("Miami",R.drawable.desert,"Worlds beatuiful beaches")
        val p2=popular("Miami",R.drawable.desert,"Worlds beatuiful beaches")
        val p3=popular("Miami",R.drawable.desert,"Worlds beatuiful beaches")
        val p4=popular("Miami",R.drawable.desert,"Worlds beatuiful beaches")
        val p= listOf(p1,p2,p3,p4)*/
        val nearbyPlaces = intent.getParcelableArrayListExtra<Place>("nearbyPlaces")
        searchButton.setOnClickListener {
            val searchQuery = searchBar.text.toString()
            performSearch(searchQuery)
        }




        recyclerView1.layoutManager=LinearLayoutManager(this@SecondActivity,LinearLayoutManager.HORIZONTAL,false)
        recyclerView2.layoutManager=LinearLayoutManager(this@SecondActivity,LinearLayoutManager.HORIZONTAL,false)

        //recyclerView1.adapter=adapter(l)
        //recyclerView2.adapter=popularadapter(p)


        val summa=findViewById<Button>(R.id.button)
        summa.setOnClickListener {
            val intent= Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }



    }
}
private fun performSearch(query: String) {
 fetchNearbyPlaces(query) // Replace with your API call


}

private fun fetchNearbyPlaces(query: String) {
    val radius=5000
    val apiKey="AIzaSyD5ekvYbjJp_KFPGgm2flRXDfEgNOt_p_o"
    val type="Lodgings"
    // TODO: Implement the Retrofit API call to fetch nearby places based on the query
    val retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/") // Base URL of the API
        .addConverterFactory(GsonConverterFactory.create()) // Converter for JSON responses
        .build()

    val placesApiService = retrofit.create(PlacesApiService::class.java)
    val call: Call<ApiResponse> = placesApiService.getNearbyPlaces(query, radius, type, apiKey)



    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val places = response.body()?.results
                val popularList = mutableListOf<popular>()

                places?.forEach { place ->
                    val popularItem = popular(place.name, R.drawable.hotel_icon, "Hotel description")
                    popularList.add(popularItem)
                }

                recyclerView2.adapter = popularadapter(popularList)
            } else {

                val errorCode = response.code()
                val errorBody = response.errorBody()?.string()

            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

        }
    })
}
