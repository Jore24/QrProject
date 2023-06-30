package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cursoklotin.intento.R
import android.util.Log
import com.cursoklotin.intento.bd.services.AdminQueryHelper
import com.cursoklotin.intento.UserData
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

        val adminQueryHelper = AdminQueryHelper(db)
        val usuarios: List<UserData> = adminQueryHelper.obtenerUsuarios()

        val usuariosNormales: List<UserData> = usuarios.filter { it.rol == "1" }
        val admins: List<UserData> = usuarios.filter { it.rol == "2" }

        // Mostrar los usuarios normales por consola
        for (usuario in usuariosNormales) {
            Log.d(
                "ListarUsersActivity",
                "Usuario Normal:\n" +
                        "ID: ${usuario.id}\n" +
                        "Nombres: ${usuario.nombres}\n" +
                        "Correo: ${usuario.correo}\n" +
                        "Contraseña: ${usuario.contrasena}\n" +
                        "Sexo: ${usuario.sexo}\n" +
                        "Teléfono: ${usuario.telefono}\n" +
                        "Número de Cuenta: ${usuario.numeroCuenta}\n" +
                        "Banco: ${usuario.banco}\n" +
                        "DNI: ${usuario.dni}\n" +
                        "Fecha de Nacimiento: ${usuario.fechaNacimiento}\n" +
                        "Jefe: ${usuario.jefe}\n" +
                        "Dirección: ${usuario.direccion}\n" +
                        "Distrito: ${usuario.distrito}\n" +
                        "Condición: ${usuario.condicion}\n" +
                        "Cargo: ${usuario.cargo}\n" +
                        "Rol: ${usuario.rol}\n" +
                        "Fecha de Creación: ${usuario.fechaCreacion}\n" +
                        "Última Actualización: ${usuario.ultimaActualizacion}\n" +
                        "Estado de Cuenta: ${usuario.estadoCuenta}\n" +
                        "Imagen de Perfil: ${usuario.imagenPerfil}"
            )
        }

        // Mostrar los admins por consola
        for (admin in admins) {
            Log.d(
                "ListarUsersActivity",
                "Admin:\n" +
                        "ID: ${admin.id}\n" +
                        "Nombres: ${admin.nombres}\n" +
                        "Correo: ${admin.correo}\n" +
                        "Contraseña: ${admin.contrasena}\n" +
                        "Sexo: ${admin.sexo}\n" +
                        "Teléfono: ${admin.telefono}\n" +
                        "Número de Cuenta: ${admin.numeroCuenta}\n" +
                        "Banco: ${admin.banco}\n" +
                        "DNI: ${admin.dni}\n" +
                        "Fecha de Nacimiento: ${admin.fechaNacimiento}\n" +
                        "Jefe: ${admin.jefe}\n" +
                        "Dirección: ${admin.direccion}\n" +
                        "Distrito: ${admin.distrito}\n" +
                        "Condición: ${admin.condicion}\n" +
                        "Cargo: ${admin.cargo}\n" +
                        "Rol: ${admin.rol}\n" +
                        "Fecha de Creación: ${admin.fechaCreacion}\n" +
                        "Última Actualización: ${admin.ultimaActualizacion}\n" +
                        "Estado de Cuenta: ${admin.estadoCuenta}\n" +
                        "Imagen de Perfil: ${admin.imagenPerfil}"
            )
        }
    }


}