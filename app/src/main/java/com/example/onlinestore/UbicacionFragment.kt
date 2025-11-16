package com.example.onlinestore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class UbicacionFragment : Fragment() {

    private lateinit var txtNombreTienda: TextView
    private lateinit var txtDireccion: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var txtHorario: TextView
    private lateinit var btnAbrirGoogleMaps: Button

    private val tiendaNombre = "RealTech - Tienda Principal"
    private val tiendaDireccion = "Centro Comercial Andino, Local 201\nCarrera 11 # 82-71\nBogotá, Colombia"
    private val tiendaTelefono = "+57 1 345 6789"
    private val tiendaHorario = "Lunes a Sábado: 9:00 AM - 8:00 PM\nDomingos: 10:00 AM - 6:00 PM"
    private val googleMapsUrl = "https://maps.app.goo.gl/HgogMpxxiXJSEPjQA"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubicacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNombreTienda = view.findViewById(R.id.txtNombreTienda)
        txtDireccion = view.findViewById(R.id.txtDireccion)
        txtTelefono = view.findViewById(R.id.txtTelefono)
        txtHorario = view.findViewById(R.id.txtHorario)
        btnAbrirGoogleMaps = view.findViewById(R.id.btnAbrirGoogleMaps)

        txtNombreTienda.text = tiendaNombre
        txtDireccion.text = tiendaDireccion
        txtTelefono.text = tiendaTelefono
        txtHorario.text = tiendaHorario

        btnAbrirGoogleMaps.setOnClickListener {
            abrirEnGoogleMaps()
        }
    }

    private fun abrirEnGoogleMaps() {
        try {
            // Intentar abrir en la app de Google Maps
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
            intent.setPackage("com.google.android.apps.maps")
            
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            } else {
                // Si no está instalada la app, abrir en el navegador
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
                startActivity(browserIntent)
            }
        } catch (e: Exception) {
            android.widget.Toast.makeText(
                requireContext(),
                "Error al abrir Google Maps: ${e.message}",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}

