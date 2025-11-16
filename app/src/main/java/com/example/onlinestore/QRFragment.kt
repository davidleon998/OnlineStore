package com.example.onlinestore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class QRFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr, container, false)
        
        // Abrir QRGeneratorActivity cuando se muestra este fragment
        val intent = Intent(requireContext(), QRGeneratorActivity::class.java)
        startActivity(intent)
        
        // Volver a la pestaña de productos después de un momento
        view.postDelayed({
            (activity as? MainActivity)?.selectProductosTab()
        }, 100)
        
        return view
    }
}

