package com.cursoklotin.intento.activitys.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.databinding.UserQrBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


class QrActivity : AppCompatActivity() {

    private lateinit var binding: UserQrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonScan.setOnClickListener { initScanner() }
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Que bueno que funciona!")
        integrator.setTorchEnabled(false)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                val qrCode = result.contents
                if (isQRCodeValid(qrCode)) {
                    // Código QR válido, redirigir al XML de "Marcar Asistencia" y mostrar mensaje de éxito
                    val intent = Intent(this, MarcarAsistenciaActivity::class.java)
                    intent.putExtra("mensaje", "Asistencia marcada")
                    startActivity(intent)
                } else {
                    // Código QR inválido, mostrar mensaje de error
                    Toast.makeText(this, "Código QR inválido", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun isQRCodeValid(qrCode: String): Boolean {
        // Aquí puedes agregar la lógica para validar el código QR
        // Por ejemplo, compararlo con valores predefinidos o consultarlo en la base de datos
        // Devuelve true si el código QR es válido, false si no lo es
        return qrCode == "test"
    }
}