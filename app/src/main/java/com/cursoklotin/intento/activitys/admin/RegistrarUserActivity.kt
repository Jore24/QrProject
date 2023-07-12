package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cursoklotin.intento.R




import com.cursoklotin.intento.bd.services.AdminQueryHelper
import com.cursoklotin.intento.utils.DateTimeUtils.getCurrentDateTime
import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.bd.DatabaseHelper


class RegistrarUserActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var etAdminName: EditText
    private lateinit var etAdminEmail: EditText
    private lateinit var etAdminPassword: EditText
    private lateinit var etAdminSexo: EditText
    private lateinit var etAdminTelefono: EditText // Agregar esta línea
    private lateinit var etAdminNumeroCuenta: EditText
    private lateinit var etAdminBanco: EditText
    private lateinit var etAdminDni: EditText
    private lateinit var etAdminFechaNacimiento: EditText
    private lateinit var etAdminJefe: EditText
    private lateinit var etAdminDireccion: EditText
    private lateinit var etAdminDistrito: EditText
    private lateinit var etAdminCondicion: EditText
    private lateinit var etAdminCargoId: EditText
    private lateinit var etAdminRol: EditText
    private lateinit var etAdminEstadoCuenta: EditText
    private lateinit var etAdminImagenPerfil: EditText
    private lateinit var btnRegisterAdmin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_registrar_usuario)

        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        etAdminName = findViewById(R.id.etAdminName)
        etAdminEmail = findViewById(R.id.etAdminEmail)
        etAdminPassword = findViewById(R.id.etAdminPassword)
        etAdminSexo = findViewById(R.id.etAdminSexo)
        etAdminTelefono = findViewById(R.id.etAdminTelefono) // Agregar esta línea
        etAdminNumeroCuenta = findViewById(R.id.etAdminNumeroCuenta)
        etAdminBanco = findViewById(R.id.etAdminBanco)
        etAdminDni = findViewById(R.id.etAdminDni)
        etAdminFechaNacimiento = findViewById(R.id.etAdminFechaNacimiento)
        etAdminJefe = findViewById(R.id.etAdminJefe)
        etAdminDireccion = findViewById(R.id.etAdminDireccion)
        etAdminDistrito = findViewById(R.id.etAdminDistrito)
        etAdminCondicion = findViewById(R.id.etAdminCondicion)
        etAdminCargoId = findViewById(R.id.etAdminCargoId)
        etAdminRol = findViewById(R.id.etAdminRol)
        etAdminEstadoCuenta = findViewById(R.id.etAdminEstadoCuenta)
        etAdminImagenPerfil = findViewById(R.id.etAdminImagenPerfil)
        btnRegisterAdmin = findViewById(R.id.btnRegisterAdmin)

        btnRegisterAdmin.setOnClickListener {
            registerAdmin()
        }
    }

    private fun registerAdmin() {
        //función para registrar un usuario
        return
    }



}
