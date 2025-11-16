package com.example.onlinestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(
    private val productos: List<Producto>
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txtNombreCarrito)
        val precio: TextView = itemView.findViewById(R.id.txtPrecioCarrito)
        val imagen: ImageView = itemView.findViewById(R.id.imgProductoCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = formatearPrecio(producto.precio)
        holder.imagen.setImageResource(producto.imagen)
    }

    private fun formatearPrecio(precio: Double): String {
        return "$${String.format("%,.0f", precio)}"
    }

    override fun getItemCount() = productos.size
}

