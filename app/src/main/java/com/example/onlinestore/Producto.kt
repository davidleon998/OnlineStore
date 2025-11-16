package com.example.onlinestore

data class Producto(
    val nombre: String,
    val precio: Double,
    val imagen: Int,
    val descripcion: String = ""
)

