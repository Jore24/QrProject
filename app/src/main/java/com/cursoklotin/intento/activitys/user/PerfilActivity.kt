package com.cursoklotin.intento.activitys.user

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R
import com.cursoklotin.intento.managers.UserManager

class PerfilActivity : AppCompatActivity() {

    private lateinit var nombresTextView: TextView
    private lateinit var correoEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var editarButton: Button
    private lateinit var guardarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_perfil)

        nombresTextView = findViewById(R.id.nombresTextView)
        correoEditText = findViewById(R.id.correoEditText)
        telefonoEditText = findViewById(R.id.telefonoEditText)
        editarButton = findViewById(R.id.editarButton)
        guardarButton = findViewById(R.id.guardarButton)

        // Obtener los datos del usuario desde el estado global (UserManager)
        val userManager = UserManager.getInstance(applicationContext)
        val personData = userManager.personaData

        // Mostrar los datos del usuario en las vistas correspondientes
        nombresTextView.text = "Nombres: ${personData?.nombres}"
        correoEditText.setText(personData?.nombres)
        telefonoEditText.setText(personData?.nombres)

        // Configurar el click listener para el botón "Editar"
        editarButton.setOnClickListener {
            // Habilitar la edición de los campos y mostrar el botón "Guardar"
            enableFieldsForEditing()
        }

        // Configurar el click listener para el botón "Guardar"
        guardarButton.setOnClickListener {
            // Obtener los nuevos valores de correo y teléfono
            val nuevoCorreo = correoEditText.text.toString()
            val nuevoTelefono = telefonoEditText.text.toString()

            // Actualizar los datos del usuario en el estado global (UserManager)
            userManager.updatePersonaData(nuevoCorreo, nuevoTelefono)

            // Mostrar los nuevos valores en las vistas correspondientes
            correoEditText.setText(nuevoCorreo)
            telefonoEditText.setText(nuevoTelefono)

            // Deshabilitar la edición de los campos y ocultar el botón "Guardar"
            disableFieldsForEditing()
        }
    }

    private fun enableFieldsForEditing() {
        correoEditText.isEnabled = true
        telefonoEditText.isEnabled = true
        editarButton.visibility = View.GONE
        guardarButton.visibility = View.VISIBLE
    }

    private fun disableFieldsForEditing() {
        correoEditText.isEnabled = false
        telefonoEditText.isEnabled = false
        editarButton.visibility = View.VISIBLE
        guardarButton.visibility = View.GONE
    }
}
