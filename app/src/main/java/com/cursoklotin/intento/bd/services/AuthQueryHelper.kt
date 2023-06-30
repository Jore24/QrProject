package com.cursoklotin.intento.bd.services

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor


class AuthQueryHelper(private val db: SQLiteDatabase) {
    @SuppressLint("Range")
    fun login(email: String, password: String): Pair<Int, String> {
        val projection = arrayOf("personaId", "rol")
        val selection = "correo = ? AND contrasena = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor: Cursor = db.query("Empleado", projection, selection, selectionArgs, null, null, null)

        val result: Pair<Int, String> = if (cursor.moveToFirst()) {
            val personaId = cursor.getInt(cursor.getColumnIndex("personaId"))
            val rol = cursor.getString(cursor.getColumnIndex("rol"))
            personaId to rol
        } else {
            -1 to ""
        }
        cursor.close()
        return result
    }


}



//    fun register(adminId: Int, userData: UserData): Boolean {
//        val adminUser = getUserById(adminId)
//        if (adminUser != null && adminUser.isAdmin) {
//            val db = readableDatabase
//            val contentValues = ContentValues().apply {
//                put("correo", userData.email)
//                put("contrasena", userData.password)
//                put("id", userData.id)
//                put("nombres", userData.nombres)
//                put("correo", userData.correo)
//                put("contrasena", userData.contrasena)
//                put("telefono", userData.telefono)
//                put("numero_cuenta", userData.numero_cuenta)
//                put("banco", userData.banco)
//                put("dni", userData.dni)
//                put("fecha_nacimiento", userData.fecha_nacimiento)
//                put("jefe", userData.jefe)
//                put("direccion", userData.direccion)
//                put("distrito", userData.distrito)
//                put("condicion", userData.condicion)
//                put("cargo_id", userData.cargo_id)
//            }
//
//            val result = db.insert("User", null, contentValues)
//            db.close()
//
//            return result != -1L
//        }
//
//        return false
//    }






