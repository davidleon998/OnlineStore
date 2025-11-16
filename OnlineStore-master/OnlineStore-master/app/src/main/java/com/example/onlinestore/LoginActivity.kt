package com.example.onlinestore

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtUsuario: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Verificar si ya está logueado
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        
        if (isLoggedIn) {
            // Si ya está logueado, ir directamente a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        
        setContentView(R.layout.activity_login)

        edtUsuario = findViewById(R.id.edtUsuario)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (validarLogin(usuario, password)) {
                // Guardar estado de login
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("usuario", usuario)
                editor.apply()

                // Ir a MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validarLogin(usuario: String, password: String): Boolean {
        // Validación simple (en producción usar autenticación segura)
        // Usuario: admin, Password: admin123
        return (usuario == "admin" && password == "admin123") ||
               (usuario == "usuario" && password == "usuario123") ||
               (usuario.isNotEmpty() && password.length >= 6) // Permitir cualquier usuario con contraseña de 6+ caracteres
    }
}

