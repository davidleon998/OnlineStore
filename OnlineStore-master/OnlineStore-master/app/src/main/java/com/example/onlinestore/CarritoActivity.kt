package com.example.onlinestore

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerViewCarrito: RecyclerView
    private lateinit var txtTotalCarrito: TextView
    private lateinit var btnFinalizarCompra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito)
        txtTotalCarrito = findViewById(R.id.txtTotalCarrito)
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra)

        actualizarVista()

        btnFinalizarCompra.setOnClickListener {
            mostrarDialogoConfirmacion()
        }
    }

    private fun actualizarVista() {
        val productos = Carrito.productos
        val adapter = CarritoAdapter(productos)
        recyclerViewCarrito.layoutManager = LinearLayoutManager(this)
        recyclerViewCarrito.adapter = adapter

        val totalCompra = Carrito.total()
        val cantidad = Carrito.cantidad()
        txtTotalCarrito.text = "Total: ${formatearPrecio(totalCompra)}\nCantidad de productos: $cantidad"
    }

    private fun mostrarDialogoConfirmacion() {
        val totalCompra = Carrito.total()
        val cantidad = Carrito.cantidad()

        AlertDialog.Builder(this)
            .setTitle("Confirmar compra")
            .setMessage("Cantidad de productos: $cantidad\nTotal a pagar: ${formatearPrecio(totalCompra)}")
            .setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(
                    this,
                    "Compra realizada con éxito",
                    Toast.LENGTH_LONG
                ).show()
                Carrito.limpiar()
                actualizarVista()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        actualizarVista()
    }

    private fun formatearPrecio(precio: Double): String {
        return "$${String.format("%,.0f", precio)}"
    }
}

