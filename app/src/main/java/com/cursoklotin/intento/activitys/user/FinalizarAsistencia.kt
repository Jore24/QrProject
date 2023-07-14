package com.cursoklotin.intento.activitys.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.cursoklotin.intento.R
import com.cursoklotin.intento.bd.services.EmployeQueryHelper
import com.cursoklotin.intento.utils.DateTimeUtils
import java.text.SimpleDateFormat
import java.util.*

class FinalizarJornada : AppCompatActivity() {

    private var btnFinalizarJornada: Button? = null
    private var btnAceptar: Button? = null
    private var btnCancelar: Button? = null
    private var txtHoraInicio: TextView? = null
    private var txtHoraFin: TextView? = null
    private var btnCancelarJornada: Button? = null
    private var btnInciarJornadaMain: Button? = null
    private var txtNombres: TextView? = null
    private var txtHorario: TextView? = null

    private var Content_Modal: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_finalizar_asistencia)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnFinalizarJornada = findViewById(R.id.btnFinalizarJornada)
        Content_Modal = findViewById(R.id.Content_Modal)
        txtHoraInicio = findViewById(R.id.txtHoraInicio)
        txtHoraFin = findViewById(R.id.txtHoraFin)
        btnCancelarJornada = findViewById(R.id.btnFinalizarJornada)
        txtNombres = findViewById(R.id.txtNombres)
        txtHorario = findViewById(R.id.txtHorario1)

        val nombres = intent.getStringExtra("nombres")
        txtNombres?.text = nombres

        setupHorario()


        //Ocultar MODAL
        Content_Modal?.visibility = View.GONE

        btnFinalizarJornada?.setOnClickListener {
            Content_Modal?.visibility = View.VISIBLE
        }

        btnCancelar?.setOnClickListener {
            Content_Modal?.visibility = View.GONE
        }
        //recibir el put extra del intent de la actividad anterior el valor es                     intent.putExtra("mensaje", formattedHoraInicio)
        val horaInicio = intent.getStringExtra("horaInicio")
        //setear el horario inicio
        txtHoraInicio?.text = "Hora de inicio: $horaInicio"


        btnAceptar?.setOnClickListener {
            Content_Modal?.visibility = View.GONE
            //setear el horario fin con el valor de la hora actual current time
            val currentDate = DateTimeUtils.getCurrentDate()
            val formattedHoraFin= SimpleDateFormat("h:mm a", Locale.getDefault()).format(currentDate.time)

            txtHoraFin?.text = "Hora Fin: $formattedHoraFin"
            btnCancelarJornada?.visibility = View.GONE
            Toast.makeText(this, "Jornada Finalizada", Toast.LENGTH_SHORT).show()
        }

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
        txtHorario?.text = "$formattedEntrada - $formattedSalida"
    }



}