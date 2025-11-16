package com.example.onlinestore

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var btnVerCarrito: Button
    private lateinit var btnEscanearQR: Button
    private lateinit var btnUbicacion: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        btnVerCarrito = findViewById(R.id.btnVerCarrito)
        btnEscanearQR = findViewById(R.id.btnEscanearQR)
        btnUbicacion = findViewById(R.id.btnUbicacion)
        btnLogout = findViewById(R.id.btnLogout)

        val listaProductos = listOf(
            Producto(
                "Audífonos Bluetooth",
                120000.0,
                R.drawable.audifonos,
                "Audífonos inalámbricos con excelente calidad de sonido y cancelación de ruido."
            ),
            Producto(
                "Mouse Inalámbrico",
                80000.0,
                R.drawable.mouse,
                "Mouse ergonómico inalámbrico con sensor óptico de alta precisión."
            ),
            Producto(
                "Teclado Gamer",
                150000.0,
                R.drawable.teclado,
                "Teclado mecánico gamer con iluminación RGB y switches de alta respuesta."
            ),
            Producto(
                "Monitor 4K",
                450000.0,
                R.drawable.monitor,
                "Monitor ultra HD de 27 pulgadas con tecnología IPS y HDR."
            ),
            Producto(
                "Webcam HD",
                95000.0,
                R.drawable.webcam,
                "Cámara web Full HD con micrófono integrado y ajuste automático de luz."
            ),
            Producto(
                "Altavoz Bluetooth",
                180000.0,
                R.drawable.altavoz,
                "Altavoz portátil con sonido estéreo y batería de larga duración."
            )
        )

        val adapter = ProductoAdapter(listaProductos) { producto ->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("imagen", producto.imagen)
            intent.putExtra("descripcion", producto.descripcion)
            startActivity(intent)
        }

        recyclerViewProductos.layoutManager = LinearLayoutManager(this)
        recyclerViewProductos.adapter = adapter

        btnVerCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        btnEscanearQR.setOnClickListener {
            val intent = Intent(this, QRGeneratorActivity::class.java)
            startActivity(intent)
        }

        btnUbicacion.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            // Cerrar sesión
            val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.remove("usuario")
            editor.apply()

            // Volver a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
