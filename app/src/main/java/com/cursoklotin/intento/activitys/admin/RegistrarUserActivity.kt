package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cursoklotin.intento.R
import com.cursoklotin.intento.models.UserData


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
        val nombres = etAdminName.text.toString()
        val correo = etAdminEmail.text.toString()
        val contrasena = etAdminPassword.text.toString()
        val sexo = etAdminSexo.text.toString()
        val telefono = etAdminTelefono.text.toString()
        val numeroCuenta = etAdminNumeroCuenta.text.toString()
        val banco = etAdminBanco.text.toString()
        val dni = etAdminDni.text.toString()
        val fechaNacimiento = etAdminFechaNacimiento.text.toString()
        val jefe = etAdminJefe.text.toString()
        val direccion = etAdminDireccion.text.toString()
        val distrito = etAdminDistrito.text.toString()
        val condicion = etAdminCondicion.text.toString()
        val cargo = etAdminCargoId.text.toString()
        val rol = etAdminRol.text.toString()
        val fechaCreacion = getCurrentDateTime() // Obtener la fecha actual
        val ultimaActualizacion = getCurrentDateTime() // Obtener la fecha actual
        val estadoCuenta = "activo" // Asignar un valor inicial para el estado de cuenta
        val imagenPerfil = "" // Dejar la imagen de perfil en blanco inicialmente

        if (nombres.isNotEmpty() && correo.isNotEmpty() && contrasena.isNotEmpty() && sexo.isNotEmpty()) {
            val adminQueryHelper = AdminQueryHelper(db)

            val userData = UserData(
                id = 0, // El ID se generará automáticamente en la base de datos
                nombres = nombres,
                correo = correo,
                contrasena = contrasena,
                sexo = sexo,
                telefono = telefono,
                numeroCuenta = numeroCuenta,
                banco = banco,
                dni = dni,
                fechaNacimiento = fechaNacimiento,
                jefe = jefe,
                direccion = direccion,
                distrito = distrito,
                condicion = condicion,
                cargo = cargo,
                rol = rol,
                fechaCreacion = fechaCreacion,
                ultimaActualizacion = ultimaActualizacion,
                estadoCuenta = estadoCuenta,
                imagenPerfil = imagenPerfil
            )

            if (adminQueryHelper.insertUser(
                    name = userData.nombres,
                    email = userData.correo,
                    password = userData.contrasena,
                    sexo = userData.sexo,
                    telefono = userData.telefono,
                    numeroCuenta = userData.numeroCuenta,
                    banco = userData.banco,
                    dni = userData.dni,
                    fechaNacimiento = userData.fechaNacimiento,
                    jefe = userData.jefe,
                    direccion = userData.direccion,
                    distrito = userData.distrito,
                    condicion = userData.condicion,
                    cargo = userData.cargo,
                    rol = userData.rol,
                    fechaCreacion = userData.fechaCreacion,
                    ultimaActualizacion = userData.ultimaActualizacion,
                    estadoCuenta = userData.estadoCuenta,
                    imagenPerfil = userData.imagenPerfil
                ) != -1L
            ) {
                Toast.makeText(this, "Administrador registrado exitosamente: ${userData.nombres}", Toast.LENGTH_SHORT).show()
                finish() // Cerrar la actividad después del registro exitoso
            } else {
                Toast.makeText(this, "Error al registrar administrador", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
