package com.cursoklotin.intento.bd.services
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import com.cursoklotin.intento.models.UserData
import com.cursoklotin.intento.models.EmpleadoData
import android.util.Log

class AdminQueryHelper(private val db: SQLiteDatabase) {

    fun insertUser(userData: UserData, empleadoData: EmpleadoData): Long {
        val personaId = insertEmpleado(empleadoData)

        //userData.personaId = personaId.toInt()
        return db.insert("User", null, userData.toContentValues())
    }

    fun obtenerUsuarios(): List<UserData> {
        val usuarios: MutableList<UserData> = mutableListOf()

        val cursor: Cursor? = db.query("User", null, null, null, null, null, null)

        cursor?.use {
            while (cursor.moveToNext()) {
                val userData = UserData(
                    idUser = cursor.getInt(cursor.getColumnIndex("id")),
                    correo = cursor.getString(cursor.getColumnIndex("correo")),
                    contrasena = cursor.getString(cursor.getColumnIndex("contrasena")),
                    rol = cursor.getInt(cursor.getColumnIndex("rol")),
                    fechaInicio = cursor.getString(cursor.getColumnIndex("fechaInicio")),
                    fechaFin = cursor.getString(cursor.getColumnIndex("fechaFin")),
                    jefe = cursor.getString(cursor.getColumnIndex("jefe")),
                    estadoCuenta = cursor.getString(cursor.getColumnIndex("estadoCuenta")),
                    empleadoId = cursor.getInt(cursor.getColumnIndex("empleadoId")),
                    cargoId = cursor.getInt(cursor.getColumnIndex("cargoId")),
                    url = cursor.getString(cursor.getColumnIndex("url"))
                )

                usuarios.add(userData)
            }
        }

        return usuarios
    }

    fun eliminarUsuarioPorId(id: Int): Int {
        return db.delete("User", "id = ?", arrayOf(id.toString()))
    }

    fun actualizarUsuarioPorId(userData: UserData): Boolean {
        val contentValues = userData.toContentValues()

        val rowsAffected = db.update("User", contentValues, "id = ?", arrayOf(userData.idUser.toString()))
        return rowsAffected > 0
    }

    private fun insertEmpleado(empleadoData: EmpleadoData): Long {
        return db.insert("Empleado", null, empleadoData.toContentValues())
    }
}

private fun UserData.toContentValues(): ContentValues {
    return ContentValues().apply {
        put("correo", correo)
        put("contrasena", contrasena)
        put("rol", rol)
        put("fechaInicio", fechaInicio)
        put("fechaFin", fechaFin)
        put("jefe", jefe)
        put("estadoCuenta", estadoCuenta)
        put("personaId", empleadoId)
        put("cargoId", cargoId)
        put("url", url)
    }
}

private fun EmpleadoData.toContentValues(): ContentValues {
    return ContentValues().apply {
        put("nombres", nombres)
        put("sexo", sexo)
        put("telefono", telefono)
        put("dni", dni)
        put("numeroCuenta", numeroCuenta)
        put("banco", banco)
        put("fechaNacimiento", fechaNacimiento)
        put("direccion", direccion)
        put("distrito", distrito)
        put("fechaCreacion", fechaCreacion)
        put("ultimaActualizacion", ultimaActualizacion)
    }
}
