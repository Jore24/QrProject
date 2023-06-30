package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cursoklotin.intento.R
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cursoklotin.intento.bd.services.AdminQueryHelper
import com.cursoklotin.intento.bd.DatabaseHelper
import android.database.sqlite.SQLiteDatabase

class EliminarUserActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_eliminar_user)

        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        val userId = obtenerUserIdDesdeIntent() // Obtén el ID del usuario que se desea eliminar desde el intent

        eliminarUsuario(userId)
    }

    private fun eliminarUsuario(id: Int) {
        val adminQueryHelper = AdminQueryHelper(db)
        val filasAfectadas = adminQueryHelper.eliminarUsuarioPorId(id)

        if (filasAfectadas > 0) {
            Log.d("EliminarUserActivity", "Usuario eliminado exitosamente")
        } else {
            Log.d("EliminarUserActivity", "No se encontró ningún usuario con el ID proporcionado")
        }
    }

    private fun obtenerUserIdDesdeIntent(): Int {
        // Código para obtener el ID del usuario desde el intent
        return 8
    }
}
