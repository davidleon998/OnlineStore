package com.example.onlinestore

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.Hashtable

class QRGeneratorActivity : AppCompatActivity() {

    private lateinit var imgQRCode: ImageView
    private lateinit var btnAbrirFormulario: Button

    // Esquema personalizado para abrir el formulario dentro de la app
    private val formularioAppUrl = "onlinestore://opinionform"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generator)

        imgQRCode = findViewById(R.id.imgQRCode)
        btnAbrirFormulario = findViewById(R.id.btnAbrirFormulario)

        // Generar QR con el esquema personalizado que abrirá el formulario en la app
        generarQRCode(formularioAppUrl)

        btnAbrirFormulario.setOnClickListener {
            abrirFormularioApp()
        }
    }

    private fun generarQRCode(url: String) {
        try {
            if (url.isEmpty()) {
                Toast.makeText(
                    this,
                    "Error al generar código QR",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            val hints = Hashtable<EncodeHintType, Any>()
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            hints[EncodeHintType.MARGIN] = 1

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512, hints)

            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
                }
            }

            imgQRCode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Toast.makeText(this, "Error al generar código QR: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun abrirFormularioApp() {
        // Abrir el formulario de opinión dentro de la aplicación
        val intent = Intent(this, OpinionFormActivity::class.java)
        startActivity(intent)
    }
}

