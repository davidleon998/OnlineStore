package com.example.onlinestore

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtUsuario: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var edtConfirmarPassword: com.google.android.material.textfield.TextInputEditText
    private lateinit var edtEmail: com.google.android.material.textfield.TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistro: Button
    private lateinit var txtCambiarModo: TextView
    private lateinit var txtTitulo: TextView
    private lateinit var sharedPreferences: SharedPreferences
    
    private var esModoRegistro = false

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
        edtConfirmarPassword = findViewById(R.id.edtConfirmarPassword)
        edtEmail = findViewById(R.id.edtEmail)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistro = findViewById(R.id.btnRegistro)
        txtCambiarModo = findViewById(R.id.txtCambiarModo)
        txtTitulo = findViewById(R.id.txtTitulo)

        // Configurar modo inicial (Login)
        actualizarVista()

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

        btnRegistro.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val confirmarPassword = edtConfirmarPassword.text.toString().trim()
            val email = edtEmail.text.toString().trim()

            if (validarRegistro(usuario, password, confirmarPassword, email)) {
                // Guardar usuario registrado (en producción esto iría a una base de datos)
                val editor = sharedPreferences.edit()
                editor.putString("usuario_$usuario", password)
                editor.putString("email_$usuario", email)
                editor.apply()

                Toast.makeText(
                    this,
                    "Registro exitoso. Ahora puedes iniciar sesión.",
                    Toast.LENGTH_LONG
                ).show()

                // Cambiar a modo login
                esModoRegistro = false
                actualizarVista()
            }
        }

        txtCambiarModo.setOnClickListener {
            esModoRegistro = !esModoRegistro
            actualizarVista()
        }
    }

    private fun actualizarVista() {
        if (esModoRegistro) {
            // Modo Registro
            txtTitulo.text = "Registro"
            btnLogin.visibility = android.view.View.GONE
            btnRegistro.visibility = android.view.View.VISIBLE
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layoutConfirmarPassword).visibility = android.view.View.VISIBLE
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layoutEmail).visibility = android.view.View.VISIBLE
            txtCambiarModo.text = "¿Ya tienes cuenta? Inicia sesión"
            
            // Limpiar campos
            edtUsuario.setText("")
            edtPassword.setText("")
            edtConfirmarPassword.setText("")
            edtEmail.setText("")
        } else {
            // Modo Login
            txtTitulo.text = "Iniciar Sesión"
            btnLogin.visibility = android.view.View.VISIBLE
            btnRegistro.visibility = android.view.View.GONE
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layoutConfirmarPassword).visibility = android.view.View.GONE
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.layoutEmail).visibility = android.view.View.GONE
            txtCambiarModo.text = "¿No tienes cuenta? Regístrate"
            
            // Limpiar campos
            edtUsuario.setText("")
            edtPassword.setText("")
        }
    }

    private fun validarLogin(usuario: String, password: String): Boolean {
        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación simple (en producción usar autenticación segura)
        // Usuario: admin, Password: admin123
        if (usuario == "admin" && password == "admin123") {
            return true
        }
        
        if (usuario == "usuario" && password == "usuario123") {
            return true
        }

        // Verificar usuarios registrados
        val passwordGuardada = sharedPreferences.getString("usuario_$usuario", null)
        return passwordGuardada == password
    }

    private fun validarRegistro(
        usuario: String,
        password: String,
        confirmarPassword: String,
        email: String
    ): Boolean {
        if (usuario.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un nombre de usuario", Toast.LENGTH_SHORT).show()
            return false
        }

        if (usuario.length < 3) {
            Toast.makeText(this, "El usuario debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese una contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmarPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor ingrese un email válido", Toast.LENGTH_SHORT).show()
            return false
        }

        // Verificar si el usuario ya existe
        val usuarioExistente = sharedPreferences.getString("usuario_$usuario", null)
        if (usuarioExistente != null) {
            Toast.makeText(this, "Este usuario ya existe. Por favor elija otro.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
