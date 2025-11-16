package com.example.onlinestore

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var btnAbrirGoogleMaps: Button
    
    // Coordenadas precisas del Centro Comercial Andino, Bogotá
    private val tiendaLocation = LatLng(4.6566, -74.0556)
    private val tiendaNombre = "RealTech - Tienda Principal"
    private val tiendaDireccion = "Centro Comercial Andino, Local 201, Bogotá"
    private val googleMapsUrl = "https://maps.app.goo.gl/HgogMpxxiXJSEPjQA"

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnAbrirGoogleMaps = findViewById(R.id.btnAbrirGoogleMaps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        btnAbrirGoogleMaps.setOnClickListener {
            abrirEnGoogleMaps()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Agregar marcador de la tienda
        mMap.addMarker(
            MarkerOptions()
                .position(tiendaLocation)
                .title(tiendaNombre)
                .snippet(tiendaDireccion)
        )

        // Mover la cámara a la ubicación de la tienda
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tiendaLocation, 15f))

        // Habilitar controles de zoom
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true

        // Solicitar permisos de ubicación si no están concedidos
        if (checkLocationPermission()) {
            mMap.isMyLocationEnabled = true
            obtenerUbicacionActual()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (::mMap.isInitialized) {
                    mMap.isMyLocationEnabled = true
                    obtenerUbicacionActual()
                }
            } else {
                Toast.makeText(
                    this,
                    "Permiso de ubicación denegado. La ubicación de la tienda se mostrará igualmente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun obtenerUbicacionActual() {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLocation = LatLng(it.latitude, it.longitude)
                    // Opcional: mostrar la ubicación actual del usuario
                    // mMap.addMarker(MarkerOptions().position(currentLocation).title("Tu ubicación"))
                }
            }
        }
    }

    private fun abrirEnGoogleMaps() {
        try {
            // Intentar abrir en la app de Google Maps
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
            intent.setPackage("com.google.android.apps.maps")
            
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Si no está instalada la app, abrir en el navegador
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
                startActivity(browserIntent)
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error al abrir Google Maps: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


