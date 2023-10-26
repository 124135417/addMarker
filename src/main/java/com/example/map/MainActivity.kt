package com.example.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.MapFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, MapsFragment())
                .commit()
        }

        // Wait for some time to ensure the map is loaded
        // and then add a marker to the map

        Handler(Looper.getMainLooper()).postDelayed({
            addMarkerToMap()
        }, 5000) // wait for 5 seconds

    }

    private fun addMarkerToMap() {
        val mapsFragment = supportFragmentManager.findFragmentById(R.id.frame_layout) as? MapsFragment
        mapsFragment?.addMarker(37.7749, -122.4194, "Marker in San Francisco")
    }
}

