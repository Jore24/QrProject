package com.cursoklotin.intento.activitys.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.cursoklotin.intento.R

class FinalizarJornada : AppCompatActivity() {

    private var btn_finalizarJornada: Button? = null
    private var btnInciarJornadaMain: Button? = null
    private var btnCancelarJornada: Button? = null
    private var horaInicio: TextView? = null
    private var horaFin: TextView? = null

    private var Content_Modal: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_finalizar_asistencia)

        btn_finalizarJornada = findViewById(R.id.btn_finalizarJornada)
        Content_Modal = findViewById(R.id.Content_Modal)
        btnInciarJornadaMain = findViewById(R.id.btn_Aceptar)
        btnCancelarJornada = findViewById(R.id.btn_cancelar)
        horaInicio = findViewById(R.id.txt_HoraInicio)
        horaFin = findViewById(R.id.txt_HoraFin)


        //Ocultar MODAL
        Content_Modal?.visibility = View.GONE

        btn_finalizarJornada?.setOnClickListener {
            Content_Modal?.visibility = View.VISIBLE

        }

        btnCancelarJornada?.setOnClickListener {
            Content_Modal?.visibility = View.GONE

        }

        btnInciarJornadaMain?.setOnClickListener {
            Content_Modal?.visibility = View.GONE
            //setear el horario fin
            horaInicio?.text = "Hora de inicio: 08:00"
            horaFin?.text = "Hora Fin: 12:00"
            btn_finalizarJornada?.visibility = View.GONE
            Toast.makeText(this, "Jornada Finalizada", Toast.LENGTH_SHORT).show()





//            val intent = Intent(this, FinalizarJornada::class.java)
//            startActivity(intent)
        }
    }



}