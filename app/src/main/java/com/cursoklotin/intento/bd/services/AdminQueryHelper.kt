package com.cursoklotin.intento.bd.services
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import com.cursoklotin.intento.UserData
import android.util.Log

class AdminQueryHelper(private val db: SQLiteDatabase) {


    fun insertUser(
        name: String,
        email: String,
        password: String,
        sexo: String,
        telefono: String,
        numeroCuenta: String,
        banco: String,
        dni: String,
        fechaNacimiento: String,
        jefe: String,
        direccion: String,
        distrito: String,
        condicion: String,
        cargo: String,
        rol: String,
        fechaCreacion: String,
        ultimaActualizacion: String,
        estadoCuenta: String,
        imagenPerfil: String
    ): Long {
        val contentValues = ContentValues().apply {
            put("nombres", name)
            put("correo", email)
            put("contrasena", password)
            put("sexo", sexo)
            put("telefono", telefono)
            put("numeroCuenta", numeroCuenta)
            put("banco", banco)
            put("dni", dni)
            put("fechaNacimiento", fechaNacimiento)
            put("jefe", jefe)
            put("direccion", direccion)
            put("distrito", distrito)
            put("condicion", condicion)
            put("cargo", cargo)
            put("rol", rol)
            put("fechaCreacion", fechaCreacion)
            put("ultimaActualizacion", ultimaActualizacion)
            put("estadoCuenta", estadoCuenta)
            put("imagenPerfil", imagenPerfil)
        }

        return db.insert("User", null, contentValues)
    }

    fun obtenerUsuarios(): List<UserData> {
        val usuarios: MutableList<UserData> = mutableListOf()

        val cursor: Cursor? = db.query("User", null, null, null, null, null, null)

        cursor?.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val nombres = cursor.getString(cursor.getColumnIndex("nombres"))
                val correo = cursor.getString(cursor.getColumnIndex("correo"))
                val contrasena = cursor.getString(cursor.getColumnIndex("contrasena"))
                val sexo = cursor.getString(cursor.getColumnIndex("sexo"))
                val telefono = cursor.getString(cursor.getColumnIndex("telefono"))
                val numeroCuenta = cursor.getString(cursor.getColumnIndex("numeroCuenta"))
                val banco = cursor.getString(cursor.getColumnIndex("banco"))
                val dni = cursor.getString(cursor.getColumnIndex("dni"))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex("fechaNacimiento"))
                val jefe = cursor.getString(cursor.getColumnIndex("jefe"))
                val direccion = cursor.getString(cursor.getColumnIndex("direccion"))
                val distrito = cursor.getString(cursor.getColumnIndex("distrito"))
                val condicion = cursor.getString(cursor.getColumnIndex("condicion"))
                val cargo = cursor.getString(cursor.getColumnIndex("cargo"))
                val rol = cursor.getString(cursor.getColumnIndex("rol"))
                val fechaCreacion = cursor.getString(cursor.getColumnIndex("fechaCreacion"))
                val ultimaActualizacion = cursor.getString(cursor.getColumnIndex("ultimaActualizacion"))
                val estadoCuenta = cursor.getString(cursor.getColumnIndex("estadoCuenta"))
                val imagenPerfil = cursor.getString(cursor.getColumnIndex("imagenPerfil"))

                val usuario = UserData(
                    id, nombres, correo, contrasena, sexo, telefono, numeroCuenta, banco, dni,
                    fechaNacimiento, jefe, direccion, distrito, condicion, cargo, rol, fechaCreacion,
                    ultimaActualizacion, estadoCuenta, imagenPerfil
                )
                usuarios.add(usuario)
            }
        }

        return usuarios
    }

    fun eliminarUsuarioPorId(id: Int): Int {
        return db.delete("User", "id = ?", arrayOf(id.toString()))
    }

    fun actualizarUsuarioPorId(id: Int, name: String, email: String, password: String, sexo: String, telefono: String, numeroCuenta: String, banco: String, dni: String, fechaNacimiento: String, jefe: String, direccion: String, distrito: String, condicion: String, cargo: String, rol: String, fechaCreacion: String, ultimaActualizacion: String, estadoCuenta: String, imagenPerfil: String): Boolean {
        val contentValues = ContentValues().apply {
            put("nombres", name)
            put("correo", email)
            put("contrasena", password)
            put("sexo", sexo)
            put("telefono", telefono)
            put("numeroCuenta", numeroCuenta)
            put("banco", banco)
            put("dni", dni)
            put("fechaNacimiento", fechaNacimiento)
            put("jefe", jefe)
            put("direccion", direccion)
            put("distrito", distrito)
            put("condicion", condicion)
            put("cargo", cargo)
            put("rol", rol)
            put("fechaCreacion", fechaCreacion)
            put("ultimaActualizacion", ultimaActualizacion)
            put("estadoCuenta", estadoCuenta)
            put("imagenPerfil", imagenPerfil)
        }

        val rowsAffected = db.update("User", contentValues, "id = ?", arrayOf(id.toString()))
        return rowsAffected > 0
    }



}