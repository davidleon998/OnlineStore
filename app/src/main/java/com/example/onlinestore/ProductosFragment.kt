package com.example.onlinestore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosFragment : Fragment() {

    private lateinit var recyclerViewProductos: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewProductos = view.findViewById(R.id.recyclerViewProductos)

        val listaProductos = listOf(
            // Tecnología - Audio
            Producto(
                "Sony WH-1000XM5 - Audífonos con Cancelación de Ruido",
                899000.0,
                R.drawable.audifonos,
                "Audífonos inalámbricos premium de Sony con cancelación de ruido activa, batería de 30 horas y calidad de sonido Hi-Res Audio. Perfectos para viajes y uso diario."
            ),
            Producto(
                "Apple AirPods Pro (2da Generación)",
                1299000.0,
                R.drawable.audifonos,
                "Audífonos inalámbricos de Apple con cancelación de ruido adaptativa, resistencia al agua IPX4 y hasta 6 horas de batería. Incluyen estuche de carga MagSafe."
            ),
            Producto(
                "JBL Flip 6 - Altavoz Bluetooth Portátil",
                349000.0,
                R.drawable.altavoz,
                "Altavoz Bluetooth resistente al agua IPX7 de JBL con sonido potente, batería de 12 horas y diseño compacto. Perfecto para fiestas y actividades al aire libre."
            ),
            Producto(
                "Bose SoundLink Revolve+ II",
                799000.0,
                R.drawable.altavoz,
                "Altavoz Bluetooth 360° de Bose con sonido omnidireccional, resistencia al agua y batería de hasta 17 horas. Diseño elegante y portátil."
            ),
            
            // Tecnología - Periféricos
            Producto(
                "Logitech MX Master 3S - Mouse Inalámbrico",
                459000.0,
                R.drawable.mouse,
                "Mouse inalámbrico profesional de Logitech con sensor de 8000 DPI, scroll MagSpeed y batería de hasta 70 días. Compatible con múltiples dispositivos."
            ),
            Producto(
                "Razer DeathAdder V3 - Mouse Gamer",
                389000.0,
                R.drawable.mouse,
                "Mouse gamer de Razer con sensor Focus Pro 30K, diseño ergonómico y switches ópticos de 90 millones de clics. Ideal para gaming competitivo."
            ),
            Producto(
                "Corsair K70 RGB TKL - Teclado Mecánico",
                699000.0,
                R.drawable.teclado,
                "Teclado mecánico gaming de Corsair con switches Cherry MX, iluminación RGB per-key y diseño tenkeyless. Construcción de aluminio anodizado."
            ),
            Producto(
                "Keychron K8 Pro - Teclado Mecánico Bluetooth",
                549000.0,
                R.drawable.teclado,
                "Teclado mecánico inalámbrico de Keychron con switches Gateron, compatibilidad Mac/Windows y diseño retroiluminado. Perfecto para trabajo y gaming."
            ),
            
            // Tecnología - Monitores
            Producto(
                "Samsung Odyssey G9 - Monitor Curvo 49\" QLED",
                3499000.0,
                R.drawable.monitor,
                "Monitor gaming ultrawide de Samsung con resolución 5120x1440, 240Hz, HDR1000 y curvatura 1000R. Tecnología QLED para colores vibrantes."
            ),
            Producto(
                "LG UltraGear 27GP850 - Monitor 4K 165Hz",
                1899000.0,
                R.drawable.monitor,
                "Monitor gaming de LG con panel IPS 4K, frecuencia de 165Hz, G-Sync compatible y tiempo de respuesta de 1ms. Ideal para gaming y diseño."
            ),
            Producto(
                "Dell UltraSharp U2723DE - Monitor 27\" 4K USB-C",
                1599000.0,
                R.drawable.monitor,
                "Monitor profesional de Dell con resolución 4K, puerto USB-C, tecnología IPS y calibración de color de fábrica. Perfecto para diseño gráfico."
            ),
            
            // Tecnología - Cámaras y Video
            Producto(
                "Logitech Brio 4K - Webcam Ultra HD",
                599000.0,
                R.drawable.webcam,
                "Webcam 4K de Logitech con HDR, corrección de luz RightLight 3.0 y micrófonos duales con cancelación de ruido. Compatible con Windows Hello."
            ),
            Producto(
                "Razer Kiyo Pro - Webcam Full HD con Iluminación",
                449000.0,
                R.drawable.webcam,
                "Webcam gaming de Razer con sensor de 1080p a 60fps, iluminación integrada y micrófono direccional. Perfecta para streaming y videollamadas."
            ),
            
            // Tecnología - Smartphones
            Producto(
                "Samsung Galaxy S24 Ultra 512GB",
                4999000.0,
                R.drawable.audifonos,
                "Smartphone flagship de Samsung con pantalla Dynamic AMOLED 2X de 6.8\", procesador Snapdragon 8 Gen 3, cámara de 200MP y S Pen integrado."
            ),
            Producto(
                "iPhone 15 Pro Max 1TB",
                6999000.0,
                R.drawable.audifonos,
                "Smartphone premium de Apple con chip A17 Pro, pantalla Super Retina XDR de 6.7\", cámara triple de 48MP y construcción de titanio. Resistente al agua IP68."
            ),
            
            // Tecnología - Tablets
            Producto(
                "iPad Pro 12.9\" M2 256GB",
                5499000.0,
                R.drawable.monitor,
                "Tablet profesional de Apple con chip M2, pantalla Liquid Retina XDR de 12.9\", compatibilidad con Apple Pencil y Magic Keyboard. Ideal para creativos."
            ),
            Producto(
                "Samsung Galaxy Tab S9 Ultra 512GB",
                3999000.0,
                R.drawable.monitor,
                "Tablet premium de Samsung con pantalla Super AMOLED de 14.6\", procesador Snapdragon 8 Gen 2 y S Pen incluido. Perfecta para productividad."
            ),
            
            // Tecnología - Laptops
            Producto(
                "MacBook Pro 16\" M3 Max 1TB",
                12999000.0,
                R.drawable.monitor,
                "Laptop profesional de Apple con chip M3 Max, pantalla Liquid Retina XDR de 16.2\", 36GB RAM y hasta 22 horas de batería. Para profesionales creativos."
            ),
            Producto(
                "ASUS ROG Zephyrus G16 - Laptop Gaming",
                8999000.0,
                R.drawable.monitor,
                "Laptop gaming de ASUS con RTX 4080, procesador Intel Core i9, pantalla QHD+ 165Hz y diseño ultradelgado. Rendimiento excepcional para gaming."
            ),
            
            // Tecnología - Smartwatches
            Producto(
                "Apple Watch Ultra 2 - Reloj Inteligente",
                3999000.0,
                R.drawable.audifonos,
                "Smartwatch premium de Apple con pantalla Always-On Retina, GPS dual, resistencia al agua hasta 100m y batería de hasta 60 horas. Para aventureros."
            ),
            Producto(
                "Samsung Galaxy Watch 6 Classic 47mm",
                1999000.0,
                R.drawable.audifonos,
                "Reloj inteligente de Samsung con bisel rotatorio físico, pantalla Super AMOLED, ECG y monitoreo de sueño avanzado. Compatible con Android."
            ),
            
            // Tecnología - Consolas
            Producto(
                "PlayStation 5 - Consola + 2 Mandos",
                2499000.0,
                R.drawable.mouse,
                "Consola de videojuegos de Sony con SSD ultrarrápido, ray tracing, salida 4K 120Hz y audio 3D. Incluye 2 mandos DualSense y 1 juego."
            ),
            Producto(
                "Xbox Series X - Consola Microsoft",
                2299000.0,
                R.drawable.mouse,
                "Consola de nueva generación de Microsoft con 4K a 60fps, ray tracing, Quick Resume y Game Pass incluido. Potencia para gaming de élite."
            ),
            
            // Tecnología - Accesorios
            Producto(
                "Oculus Quest 3 - Gafas de Realidad Virtual",
                3999000.0,
                R.drawable.audifonos,
                "Gafas VR todo-en-uno de Meta con procesador Snapdragon XR2 Gen 2, resolución 4K+ y seguimiento de manos. Experiencia inmersiva sin cables."
            ),
            Producto(
                "DJI Mini 4 Pro - Drone con Cámara 4K",
                3499000.0,
                R.drawable.webcam,
                "Drone compacto de DJI con cámara 4K/60fps, detección de obstáculos omnidireccional y vuelo de hasta 34 minutos. Perfecto para contenido aéreo."
            )
        )

        val adapter = ProductoAdapter(listaProductos) { producto ->
            val intent = Intent(requireContext(), DetalleActivity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("imagen", producto.imagen)
            intent.putExtra("descripcion", producto.descripcion)
            startActivity(intent)
        }

        recyclerViewProductos.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewProductos.adapter = adapter
    }
}

