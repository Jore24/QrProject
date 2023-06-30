package com.cursoklotin.intento.activitys.admin
import com.cursoklotin.intento.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class HomeAdminActivity : AppCompatActivity() {

    private lateinit var registrarUserButton: Button
    private lateinit var actualizarUserButton: Button
    private lateinit var eliminarUserButton: Button
    private lateinit var listarUsersButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_home)

        registrarUserButton = findViewById(R.id.RegistrarUserButton)
        actualizarUserButton = findViewById(R.id.ActualizarUserButton)
        eliminarUserButton = findViewById(R.id.EliminarUserButton)
        listarUsersButton = findViewById(R.id.ListarUsersButton)

        registrarUserButton.setOnClickListener {
            val intent = Intent(this, RegistrarUserActivity::class.java)
            startActivity(intent)
        }

        actualizarUserButton.setOnClickListener {
            val intent = Intent(this, ActualizarUserActivity::class.java)
            startActivity(intent)
        }

        eliminarUserButton.setOnClickListener {
            val intent = Intent(this, EliminarUserActivity::class.java)
            startActivity(intent)
        }

        listarUsersButton.setOnClickListener {
            val intent = Intent(this, ListarUsersActivity::class.java)
            startActivity(intent)
        }
    }
}







