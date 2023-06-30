package com.cursoklotin.intento.activitys.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R
import com.cursoklotin.intento.managers.UserManager
import android.widget.Toast
import com.cursoklotin.intento.activitys.user.*


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var userManager: UserManager
    private lateinit var cerrarSesionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_home)
        userManager = UserManager.getInstance(applicationContext)
        val personData = userManager.personaData
        if (personData != null) {
            Toast.makeText(this, "Bienvenido ${personData.nombres}", Toast.LENGTH_LONG).show()
        }

        // Configurar los listeners de clic para los botones
        cerrarSesionButton = findViewById(R.id.cerrarSesionButton)
        findViewById<View>(R.id.buttonPerfil).setOnClickListener(this)
        findViewById<View>(R.id.buttonMarcarAsistencia).setOnClickListener(this)
        findViewById<View>(R.id.buttonHorario).setOnClickListener(this)
        findViewById<View>(R.id.buttonRegistroAsistencias).setOnClickListener(this)
        findViewById<View>(R.id.buttonBoleta).setOnClickListener(this)

        cerrarSesionButton.setOnClickListener {
            // Reiniciar el estado global y cerrar la sesión
            userManager.resetPersonaData()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!userManager.isLoggedIn()) {
            // El usuario no ha iniciado sesión, redirigir al inicio de sesión
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
        } else {
            // El usuario ha iniciado sesión, acceder a los datos del usuario
            val personData = userManager.personaData
            if (personData != null) {
                // Los datos del usuario están disponibles, puedes utilizarlos
                // Aquí puedes actualizar la interfaz de usuario con los datos del usuario si es necesario
            }
        }
    }

    override fun onClick(view: View) {
        // Manejar los eventos de clic en los botones
        when (view.id) {
            R.id.buttonPerfil -> {
                // Navegar a la pantalla de Perfil
                userManager = UserManager.getInstance(applicationContext)
                val personData = userManager.personaData
                Toast.makeText(this, "Bienvenido ${personData?.nombres}", Toast.LENGTH_LONG).show()
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonMarcarAsistencia -> {
                // Navegar a la pantalla de Marcar Asistencia
                val intent = Intent(this, MarcarAsistenciaActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonHorario -> {
                // Navegar a la pantalla de Horario
                val intent = Intent(this, HorarioActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonRegistroAsistencias -> {
                // Navegar a la pantalla de Registro de Asistencias
                val intent = Intent(this, RegistroAsistenciasActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonBoleta -> {
                // Navegar a la pantalla de Boleta
                val intent = Intent(this, BoletaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}