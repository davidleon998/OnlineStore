package com.example.onlinestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var txtQRContent: TextView
    private lateinit var edtNombre: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtDireccion: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        txtQRContent = findViewById(R.id.txtQRContent)
        edtNombre = findViewById(R.id.edtNombre)
        edtEmail = findViewById(R.id.edtEmail)
        edtTelefono = findViewById(R.id.edtTelefono)
        edtDireccion = findViewById(R.id.edtDireccion)
        btnEnviar = findViewById(R.id.btnEnviar)

        // Obtener contenido del QR
        val qrContent = intent.getStringExtra("qr_content") ?: ""
        txtQRContent.text = "Código QR: $qrContent"

        btnEnviar.setOnClickListener {
            if (validarFormulario()) {
                enviarFormulario()
            } else {
                Toast.makeText(
                    this,
                    "Por favor complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validarFormulario(): Boolean {
        return edtNombre.text.toString().isNotBlank() &&
                edtEmail.text.toString().isNotBlank() &&
                edtTelefono.text.toString().isNotBlank() &&
                edtDireccion.text.toString().isNotBlank()
    }

    private fun enviarFormulario() {
        val nombre = edtNombre.text.toString()
        val email = edtEmail.text.toString()
        val telefono = edtTelefono.text.toString()
        val direccion = edtDireccion.text.toString()
        val qrContent = intent.getStringExtra("qr_content") ?: ""

        // Aquí se podría enviar a un servidor
        // Por ahora solo mostramos un mensaje
        Toast.makeText(
            this,
            "Formulario enviado exitosamente\nNombre: $nombre\nEmail: $email\nTeléfono: $telefono\nDirección: $direccion\nQR: $qrContent",
            Toast.LENGTH_LONG
        ).show()

        // Limpiar campos
        edtNombre.setText("")
        edtEmail.setText("")
        edtTelefono.setText("")
        edtDireccion.setText("")
    }
}


