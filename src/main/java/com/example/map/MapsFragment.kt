package com.example.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private val pendingMarkers = mutableListOf<MarkerOptions>()

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap

        // Example: Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Add any pending markers that were added before the map was ready
        for (markerOptions in pendingMarkers) {
            mMap.addMarker(markerOptions)
        }
        pendingMarkers.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)

        return rootView
    }

    fun addMarker(latitude: Double, longitude: Double, title: String) {
        val location = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions().position(location).title(title)

        if (::mMap.isInitialized) {
            mMap.addMarker(markerOptions)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        } else {
            pendingMarkers.add(markerOptions)
        }
    }

}