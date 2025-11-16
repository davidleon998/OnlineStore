package com.example.onlinestore

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class OpinionFormActivity : AppCompatActivity() {

    // Sección 1: Datos del Usuario
    private lateinit var edtNombreCompleto: EditText
    private lateinit var edtEmail: EditText
    private lateinit var rgRangoEdad: RadioGroup

    // Sección 2: Experiencia dentro de la Aplicación
    private lateinit var ratingExperienciaGeneral: RatingBar
    private lateinit var rgFacilidadRegistro: RadioGroup
    private lateinit var ratingOrganizacionProductos: RatingBar
    private lateinit var ratingCarritoCompras: RatingBar
    private lateinit var rgUsoQR: RadioGroup
    private lateinit var rgUtilidadMapa: RadioGroup

    // Sección 3: Opinión y Sugerencias
    private lateinit var edtAspectosGustaron: EditText
    private lateinit var edtAspectosMejorar: EditText
    private lateinit var rgRecomendacion: RadioGroup

    private lateinit var btnEnviar: Button
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinion_form)

        inicializarVistas()
        configurarListeners()
    }

    private fun inicializarVistas() {
        // Sección 1: Datos del Usuario
        edtNombreCompleto = findViewById(R.id.edtNombreCompleto)
        edtEmail = findViewById(R.id.edtEmail)
        rgRangoEdad = findViewById(R.id.rgRangoEdad)

        // Sección 2: Experiencia dentro de la Aplicación
        ratingExperienciaGeneral = findViewById(R.id.ratingExperienciaGeneral)
        rgFacilidadRegistro = findViewById(R.id.rgFacilidadRegistro)
        ratingOrganizacionProductos = findViewById(R.id.ratingOrganizacionProductos)
        ratingCarritoCompras = findViewById(R.id.ratingCarritoCompras)
        rgUsoQR = findViewById(R.id.rgUsoQR)
        rgUtilidadMapa = findViewById(R.id.rgUtilidadMapa)

        // Sección 3: Opinión y Sugerencias
        edtAspectosGustaron = findViewById(R.id.edtAspectosGustaron)
        edtAspectosMejorar = findViewById(R.id.edtAspectosMejorar)
        rgRecomendacion = findViewById(R.id.rgRecomendacion)

        btnEnviar = findViewById(R.id.btnEnviar)
        scrollView = findViewById(R.id.scrollView)
    }

    private fun configurarListeners() {
        btnEnviar.setOnClickListener {
            if (validarFormulario()) {
                enviarFormulario()
            } else {
                Toast.makeText(
                    this,
                    "Por favor complete todos los campos obligatorios",
                    Toast.LENGTH_LONG
                ).show()
                // Scroll al primer campo con error
                scrollView.smoothScrollTo(0, 0)
            }
        }
    }

    private fun validarFormulario(): Boolean {
        var esValido = true

        // Validar nombre completo (obligatorio)
        if (edtNombreCompleto.text.toString().trim().isEmpty()) {
            edtNombreCompleto.error = "Este campo es obligatorio"
            esValido = false
        } else {
            edtNombreCompleto.error = null
        }

        // Email es opcional, pero si se ingresa debe ser válido
        val email = edtEmail.text.toString().trim()
        if (email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Ingrese un email válido"
            esValido = false
        } else {
            edtEmail.error = null
        }

        // Validar rango de edad (obligatorio)
        if (rgRangoEdad.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor seleccione un rango de edad", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar experiencia general (obligatorio)
        if (ratingExperienciaGeneral.rating == 0f) {
            Toast.makeText(this, "Por favor califique su experiencia general", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar facilidad de registro (obligatorio)
        if (rgFacilidadRegistro.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor seleccione una opción sobre el registro", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar organización de productos (obligatorio)
        if (ratingOrganizacionProductos.rating == 0f) {
            Toast.makeText(this, "Por favor califique la organización de productos", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar carrito de compras (obligatorio)
        if (ratingCarritoCompras.rating == 0f) {
            Toast.makeText(this, "Por favor califique el carrito de compras", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar uso de QR (obligatorio)
        if (rgUsoQR.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor seleccione una opción sobre el uso del QR", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar utilidad del mapa (obligatorio)
        if (rgUtilidadMapa.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor seleccione una opción sobre el mapa", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar aspectos que gustaron (obligatorio)
        if (edtAspectosGustaron.text.toString().trim().isEmpty()) {
            edtAspectosGustaron.error = "Este campo es obligatorio"
            esValido = false
        } else {
            edtAspectosGustaron.error = null
        }

        // Validar aspectos a mejorar (obligatorio)
        if (edtAspectosMejorar.text.toString().trim().isEmpty()) {
            edtAspectosMejorar.error = "Este campo es obligatorio"
            esValido = false
        } else {
            edtAspectosMejorar.error = null
        }

        // Validar recomendación (obligatorio)
        if (rgRecomendacion.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor seleccione si recomendaría la aplicación", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        return esValido
    }

    private fun enviarFormulario() {
        // Recopilar todos los datos
        val nombreCompleto = edtNombreCompleto.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        
        val rangoEdad = when (rgRangoEdad.checkedRadioButtonId) {
            R.id.rbEdadMenos18 -> "Menos de 18 años"
            R.id.rbEdad18_25 -> "18 a 25 años"
            R.id.rbEdad26_35 -> "26 a 35 años"
            R.id.rbEdad36_50 -> "36 a 50 años"
            R.id.rbEdadMas50 -> "Más de 50 años"
            else -> "No especificado"
        }

        val experienciaGeneral = ratingExperienciaGeneral.rating.toInt()
        
        val facilidadRegistro = when (rgFacilidadRegistro.checkedRadioButtonId) {
            R.id.rbRegistroMuyFacil -> "Muy fácil, el proceso fue claro y rápido"
            R.id.rbRegistroConfuso -> "Un poco confuso, pero logré hacerlo"
            R.id.rbRegistroVariasVeces -> "Tuve que intentarlo varias veces"
            R.id.rbRegistroNoPude -> "No pude completar el inicio de sesión"
            else -> "No especificado"
        }

        val organizacionProductos = ratingOrganizacionProductos.rating.toInt()
        val carritoCompras = ratingCarritoCompras.rating.toInt()
        
        val usoQR = when (rgUsoQR.checkedRadioButtonId) {
            R.id.rbQRSiFunciono -> "Sí, y funcionó correctamente"
            R.id.rbQRSiProblemas -> "Sí, pero tuve problemas al escanearlo"
            R.id.rbQRNoUsado -> "No, aún no lo he usado"
            R.id.rbQRNoSabia -> "No sabía que existía esa función"
            else -> "No especificado"
        }

        val utilidadMapa = when (rgUtilidadMapa.checkedRadioButtonId) {
            R.id.rbMapaSiUtil -> "Sí, me ayudó a ubicar la tienda fácilmente"
            R.id.rbMapaSiMejorar -> "Sí, pero podría ser más detallada"
            R.id.rbMapaNoUtil -> "No me resultó útil"
            R.id.rbMapaNoUsado -> "No la he usado todavía"
            else -> "No especificado"
        }

        val aspectosGustaron = edtAspectosGustaron.text.toString().trim()
        val aspectosMejorar = edtAspectosMejorar.text.toString().trim()
        
        val recomendacion = when (rgRecomendacion.checkedRadioButtonId) {
            R.id.rbRecomendacionSi -> "Sí, definitivamente la recomendaría"
            R.id.rbRecomendacionTalvez -> "Tal vez, si mejoran algunos aspectos"
            R.id.rbRecomendacionNo -> "No la recomendaría por ahora"
            else -> "No especificado"
        }

        // Crear objeto con los datos (para futura conexión a base de datos)
        val datosFormulario = DatosFormularioOpinion(
            nombreCompleto = nombreCompleto,
            email = email,
            rangoEdad = rangoEdad,
            experienciaGeneral = experienciaGeneral,
            facilidadRegistro = facilidadRegistro,
            organizacionProductos = organizacionProductos,
            carritoCompras = carritoCompras,
            usoQR = usoQR,
            utilidadMapa = utilidadMapa,
            aspectosGustaron = aspectosGustaron,
            aspectosMejorar = aspectosMejorar,
            recomendacion = recomendacion
        )

        // TODO: Aquí se puede conectar a la base de datos
        // Por ahora solo mostramos un mensaje de éxito
        mostrarMensajeExito(datosFormulario)
        
        // Limpiar formulario después de enviar
        limpiarFormulario()
    }

    private fun mostrarMensajeExito(datos: DatosFormularioOpinion) {
        val mensaje = """
            ¡Formulario enviado exitosamente!
            
            Gracias por tu opinión, ${datos.nombreCompleto}.
            
            Tus respuestas nos ayudarán a mejorar la aplicación.
            
            En el futuro, estos datos se guardarán automáticamente en nuestra base de datos.
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Formulario Enviado")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun limpiarFormulario() {
        edtNombreCompleto.setText("")
        edtEmail.setText("")
        rgRangoEdad.clearCheck()
        ratingExperienciaGeneral.rating = 0f
        rgFacilidadRegistro.clearCheck()
        ratingOrganizacionProductos.rating = 0f
        ratingCarritoCompras.rating = 0f
        rgUsoQR.clearCheck()
        rgUtilidadMapa.clearCheck()
        edtAspectosGustaron.setText("")
        edtAspectosMejorar.setText("")
        rgRecomendacion.clearCheck()
    }

    // Clase de datos para almacenar las respuestas (preparada para conexión a BD)
    data class DatosFormularioOpinion(
        val nombreCompleto: String,
        val email: String,
        val rangoEdad: String,
        val experienciaGeneral: Int,
        val facilidadRegistro: String,
        val organizacionProductos: Int,
        val carritoCompras: Int,
        val usoQR: String,
        val utilidadMapa: String,
        val aspectosGustaron: String,
        val aspectosMejorar: String,
        val recomendacion: String
    )
}

