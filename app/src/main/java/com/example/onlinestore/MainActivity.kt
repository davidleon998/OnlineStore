package com.example.onlinestore

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)
        btnLogout = findViewById(R.id.btnLogout)
        
        btnLogout.setOnClickListener {
            cerrarSesion()
        }

        // Configurar navegación
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_productos -> {
                    cambiarFragment(ProductosFragment())
                    true
                }
                R.id.nav_carrito -> {
                    cambiarFragment(CarritoFragment())
                    true
                }
                R.id.nav_qr -> {
                    cambiarFragment(QRFragment())
                    true
                }
                R.id.nav_ubicacion -> {
                    cambiarFragment(UbicacionFragment())
                    true
                }
                else -> false
            }
        }

        // Mostrar fragment de productos por defecto
        if (savedInstanceState == null) {
            cambiarFragment(ProductosFragment())
            bottomNavigation.selectedItemId = R.id.nav_productos
        }
    }

    private fun cambiarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun selectProductosTab() {
        bottomNavigation.selectedItemId = R.id.nav_productos
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                cerrarSesion()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cerrarSesion() {
        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.remove("usuario")
        editor.apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
