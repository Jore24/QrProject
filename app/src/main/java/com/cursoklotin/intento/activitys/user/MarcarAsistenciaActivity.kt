package com.cursoklotin.intento.activitys.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R
import com.cursoklotin.intento.databinding.UserMarcarAsistenciaBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MarcarAsistenciaActivity : AppCompatActivity() {

    private lateinit var binding: UserMarcarAsistenciaBinding
    private var btnFinalizarJornada: Button? = null
    private var btn_cancelarModal: Button? = null
    private var contentModal: LinearLayout? = null
    private var horaInicio: TextView? = null
    private var horaFin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserMarcarAsistenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAceptar.setOnClickListener {
            //setear horaInicio
            showScanner()
        }

        btnFinalizarJornada = findViewById(R.id.btn_finalizarJornada)
        contentModal = findViewById(R.id.modal_jornada)
        btn_cancelarModal = findViewById(R.id.btn_cancelarModal)
        horaInicio = findViewById(R.id.txt_HoraInicio)
        horaFin = findViewById(R.id.txt_HoraFin)


        contentModal?.visibility = View.GONE

        btnFinalizarJornada?.setOnClickListener {
            contentModal?.visibility = View.VISIBLE
            //showScanner()
        }

        btn_cancelarModal?.setOnClickListener {
            contentModal?.visibility = View.GONE
        }
    }

    private fun showScanner() {
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
                    val intent = Intent(this, FinalizarJornada::class.java)
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
