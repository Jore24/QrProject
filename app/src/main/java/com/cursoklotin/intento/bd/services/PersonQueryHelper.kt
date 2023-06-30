package com.cursoklotin.intento.bd.services

import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.models.EmpleadoData
import android.util.Log

class PersonQueryHelper(private val db: SQLiteDatabase) {
    fun getPersonById(personId: Int): EmpleadoData? {
        val columns = arrayOf(
            "idPersona",
            "nombres",
            "sexo",
            "telefono",
            "numeroCuenta",
            "banco",
            "fechaNacimiento",
            "direccion",
            "distrito",
            "fechaCreacion",
            "ultimaActualizacion"
        )
        val selection = "idPersona = ?"
        val selectionArgs = arrayOf(personId.toString())

        val cursor = db.query("Persona", columns, selection, selectionArgs, null, null, null)
        var personaData: EmpleadoData? = null

        if (cursor.moveToFirst()) {
            val idPersona = cursor.getInt(cursor.getColumnIndex("idPersona"))
            val nombres = cursor.getString(cursor.getColumnIndex("nombres"))
            val sexo = cursor.getString(cursor.getColumnIndex("sexo"))
            val telefono = cursor.getString(cursor.getColumnIndex("telefono"))
            val numeroCuenta = cursor.getString(cursor.getColumnIndex("numeroCuenta"))
            val banco = cursor.getString(cursor.getColumnIndex("banco"))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex("fechaNacimiento"))
            val direccion = cursor.getString(cursor.getColumnIndex("direccion"))
            val distrito = cursor.getString(cursor.getColumnIndex("distrito"))
            val fechaCreacion = cursor.getString(cursor.getColumnIndex("fechaCreacion"))
            val ultimaActualizacion = cursor.getString(cursor.getColumnIndex("ultimaActualizacion"))

            personaData = EmpleadoData(
                idPersona,
                nombres,
                sexo,
                telefono,
                numeroCuenta,
                banco,
                fechaNacimiento,
                direccion,
                distrito,
                fechaCreacion,
                ultimaActualizacion
            )
        }

        cursor.close()

        if (personaData != null) {
            Log.d("UserQueryHelper", "Persona encontrada: $personaData")
        } else {
            Log.d("UserQueryHelper", "Persona no encontrada para el ID: $personId")
        }

        return personaData
    }

}