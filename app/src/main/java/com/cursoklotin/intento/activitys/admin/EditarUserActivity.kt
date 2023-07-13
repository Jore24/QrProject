package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cursoklotin.intento.R

class EditarUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_editar_user)
        val idUsuario = intent.getIntExtra("idUsuario", 0) // 0 es el valor predeterminado si no se encuentra el extra
        val idEmpleado = intent.getIntExtra("idEmpleado", 0)
        Toast.makeText(this, idUsuario.toString(), Toast.LENGTH_SHORT).show()

    }
}