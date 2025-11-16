package com.example.onlinestore

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val precio = intent.getDoubleExtra("precio", 0.0)
        val imagen = intent.getIntExtra("imagen", 0)
        val descripcion = intent.getStringExtra("descripcion") ?: ""

        val imgProductoDetalle: ImageView = findViewById(R.id.imgProductoDetalle)
        val txtNombreDetalle: TextView = findViewById(R.id.txtNombreDetalle)
        val txtPrecioDetalle: TextView = findViewById(R.id.txtPrecioDetalle)
        val txtDescripcionDetalle: TextView = findViewById(R.id.txtDescripcionDetalle)
        val btnAgregarCarrito: Button = findViewById(R.id.btnAgregarCarrito)

        imgProductoDetalle.setImageResource(imagen)
        txtNombreDetalle.text = nombre
        txtPrecioDetalle.text = formatearPrecio(precio)
        txtDescripcionDetalle.text = descripcion

        btnAgregarCarrito.setOnClickListener {
            val producto = Producto(nombre, precio, imagen, descripcion)
            Carrito.agregar(producto)
            Toast.makeText(
                this,
                "Producto agregado al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun formatearPrecio(precio: Double): String {
        return "$${String.format("%,.0f", precio)}"
    }
}

