package com.example.onlinestore

object Carrito {
    val productos = mutableListOf<Producto>()

    fun agregar(producto: Producto) {
        productos.add(producto)
    }

    fun total(): Double {
        return productos.sumOf { it.precio }
    }

    fun limpiar() {
        productos.clear()
    }

    fun cantidad(): Int {
        return productos.size
    }
}

