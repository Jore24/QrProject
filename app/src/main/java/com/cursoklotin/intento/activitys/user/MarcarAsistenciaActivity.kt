package com.cursoklotin.intento.activitys.user
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R


class MarcarAsistenciaActivity : AppCompatActivity() {

    private lateinit var btnMarcarAsistencia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_marcar_asistencia)
        val tvMensaje: TextView = findViewById(R.id.tvMensaje)
        val mensaje: String? = intent.getStringExtra("mensaje")
        if (!mensaje.isNullOrEmpty()) {
            tvMensaje.text = mensaje
        }


        btnMarcarAsistencia = findViewById(R.id.btnMarcarAsistencia)
        btnMarcarAsistencia.setOnClickListener {
            // Redirigir a la actividad QR
            val intent = Intent(this, QrActivity::class.java)
            startActivity(intent)
        }

        // Aquí puedes agregar el código para el botón "Finalizar Jornada"
        // ...

    }
}
