package com.cursoklotin.intento.activitys.user

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.bd.DatabaseHelper
import com.cursoklotin.intento.bd.services.EmployeQueryHelper
import com.cursoklotin.intento.databinding.UserMarcarAsistenciaBinding
import com.cursoklotin.intento.managers.UserManager
import com.cursoklotin.intento.utils.DateTimeUtils
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import java.text.SimpleDateFormat
import java.util.*

class MarcarAsistenciaActivity : AppCompatActivity() {
    private lateinit var employeQueryHelper: EmployeQueryHelper
    private lateinit var binding: UserMarcarAsistenciaBinding
    private var btnFinalizarJornada: Button? = null
    private var btn_cancelarModal: Button? = null
    private var contentModal: LinearLayout? = null
    private lateinit var txtFecha: TextView
    private lateinit var txtHorario: TextView
    private lateinit var txtNombres: TextView
    private lateinit var userManager: UserManager
    private lateinit var txtHoraInicio: TextView
    private lateinit var txtHoraFin: TextView
    private var idAsistencia: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserMarcarAsistenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userManager = UserManager.getInstance(applicationContext)
        val empleadoData = userManager.empleadoData

        binding.btnAceptar.setOnClickListener {
            //setear horaInicio
            showScanner()
        }

        btnFinalizarJornada = findViewById(R.id.btn_finalizarJornada1)
        contentModal = findViewById(R.id.modal_jornada1)
        btn_cancelarModal = findViewById(R.id.btn_cancelarModal1)
        txtHoraInicio = findViewById(R.id.txtHoraInicio1)
        txtHoraFin = findViewById(R.id.txtHoraFin1)
        txtFecha = findViewById(R.id.txtFecha1)
        txtHorario = findViewById(R.id.txtHorario1)
        txtNombres = findViewById(R.id.txtNombres1)

        //usar el empleadoData.nombre para mostrar el nombre del empleado
        txtNombres.text = empleadoData?.nombres.toString()

        //el txt Fecha tendrá la fecha actual en formato calendar y se mostrará en el txtFecha
        val currentDate = DateTimeUtils.getCurrentDate()
        println("currentDate calendar?: $currentDate")

        val formattedFecha = SimpleDateFormat("dd 'de' MMMM 'del' yyyy", Locale.getDefault()).format(currentDate.time)
        txtFecha.text = formattedFecha
        txtHoraInicio.text = "00:00"
        txtHoraFin.text = "00:00"
        val fecha = txtFecha.text.toString()
        println("fecha: $fecha")
        println("formattedFecha: ${formattedFecha}")



        employeQueryHelper = EmployeQueryHelper(this)
        setupHorario()

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
                    val currentDate = DateTimeUtils.getCurrentDate()
                    val formattedHoraInicio = SimpleDateFormat("h:mm a", Locale.getDefault()).format(currentDate.time)
                    val empleadoId = userManager.empleadoId
                    val horaSalida = Calendar.getInstance()
                    horaSalida.set(Calendar.HOUR_OF_DAY, 0) // Hora de salida a las 6:00 PM
                    horaSalida.set(Calendar.MINUTE, 0)

                    val marcarHoraEntradaAsistencia = employeQueryHelper.marcarHoraEntradaAsistencia(empleadoId!!, 1, currentDate, currentDate, horaSalida, 1 )
                    println("marcarHoraEntradaAsistencia: $marcarHoraEntradaAsistencia")
                    Toast.makeText(this, "Código QR válido", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, FinalizarJornada::class.java)
                    intent.putExtra("horaInicio", formattedHoraInicio)
                    intent.putExtra("nombres", txtNombres.text.toString())
                    intent.putExtra("horario", txtHorario.text.toString())
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
        val employeQueryHelper = EmployeQueryHelper(this)
        val qrs = employeQueryHelper.getQr()
        println(qrs[0])
        println(qrs[1])
        println(qrs[2])
        Toast.makeText(this, "Código QR válido", Toast.LENGTH_LONG).show()
        for (qr in qrs) {
            if (qr.codigo == qrCode ) {
                return true
            }
        }

        return false
    }

    private fun setupHorario() {
        val employeQueryHelper = EmployeQueryHelper(this)
        val getHorarioAsignadoByIdEmpleado = employeQueryHelper.getHorarioAsignadoByIdEmpleado(1)
        val formatoHora = SimpleDateFormat("h:mm a", Locale.getDefault())
        val salida = getHorarioAsignadoByIdEmpleado?.salida?.time
        val entrada = getHorarioAsignadoByIdEmpleado?.entrada?.time
        val formattedSalida = if (salida != null) formatoHora.format(salida) else ""
        val formattedEntrada = if (entrada != null) formatoHora.format(entrada) else ""
        println("getHorarioAsignadoByIdEmpleado: $formattedSalida")
        println("getHorarioAsignadoByIdEmpleado: $formattedEntrada")
        txtHorario.text = "$formattedEntrada - $formattedSalida"
    }

}
