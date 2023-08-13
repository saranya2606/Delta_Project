package com.example.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.GoogleMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start=findViewById<ImageView>(R.id.ivstart)
        start.setOnClickListener {
            val intent=Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }




        // Obtain the SupportMapFragment from the layout XML
        /*val mapFragment = SupportMapFragment.newInstance()

        // Replace the FrameLayout container with the SupportMapFragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mapContainer, mapFragment)
        fragmentTransaction.commit()

        // Load the map asynchronously
        mapFragment.getMapAsync { googleMap ->
            // The map is loaded and ready to use. You can perform map-related operations here.
            // For example, set up markers, add user's location, etc.
        }
    }*/
    }
}
