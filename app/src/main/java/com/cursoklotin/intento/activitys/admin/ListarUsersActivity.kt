package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cursoklotin.intento.R
import android.util.Log
import com.cursoklotin.intento.bd.services.AdminQueryHelper
import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.bd.DatabaseHelper


class ListarUsersActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_listar_users)

        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
        listarUsuarios() //Aquí está listando todos los usuarios de la base de datos!
    }


    private fun listarUsuarios() {

//        val adminQueryHelper = AdminQueryHelper(db)
//        val usuarios: List<UserData> = adminQueryHelper.obtenerUsuarios()
//
//        val usuariosNormales: List<UserData> = usuarios.filter { it.rol == "1" }
//        val admins: List<UserData> = usuarios.filter { it.rol == "2" }

        return

    }
}