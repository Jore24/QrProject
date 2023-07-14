package com.cursoklotin.intento.bd.services
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import com.cursoklotin.intento.models.UserData
import com.cursoklotin.intento.models.EmpleadoData
import android.util.Log
import com.cursoklotin.intento.bd.DatabaseHelper
import com.cursoklotin.intento.models.CargoData
import com.cursoklotin.intento.models.HorarioData

class AdminQueryHelper(private val context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper.getInstance(context)
    private val db: SQLiteDatabase = dbHelper.readableDatabase

    fun insertarUsuario(userData: UserData): Long {
        val contentValues = ContentValues().apply {
            put("correo", userData.correo)
            put("contrasena", userData.contrasena)
            put("rol", userData.rol)
            put("fechaInicio", userData.fechaInicio)
            put("fechaFin", userData.fechaFin)
            put("jefe", userData.jefe)
            put("estadoCuenta", userData.estadoCuenta)
            put("empleadoId", userData.empleadoId)
            put("cargoId", userData.cargoId)
            put("url", userData.url)
        }

        val idUsuario = db.insert("Usuario", null, contentValues)
        userData.idUser = idUsuario.toInt() // Asignar el valor del ID al objeto userData
        Log.d("Datos del usuarioData", userData.toString())


        return idUsuario
    }


    fun insertarEmpleado(empleadoData: EmpleadoData): Long {
        val contentValues = ContentValues().apply {
            //put("idEmpleado", empleadoData.idEmpleado)
            put("idHorario", empleadoData.idHorario)
            put("nombres", empleadoData.nombres)
            put("sexo", empleadoData.sexo)
            put("telefono", empleadoData.telefono)
            put("dni", empleadoData.dni)
            put("numeroCuenta", empleadoData.numeroCuenta)
            put("banco", empleadoData.banco)
            put("fechaNacimiento", empleadoData.fechaNacimiento)
            put("direccion", empleadoData.direccion)
            put("distrito", empleadoData.distrito)
            put("fechaCreacion", empleadoData.fechaCreacion)
            put("ultimaActualizacion", empleadoData.ultimaActualizacion)
        }

        val empleadoId = db.insert("Empleado", null, contentValues)
        empleadoData.idEmpleado = empleadoId.toInt()
        Log.d("Datos del empleado", empleadoData.toString())
        return empleadoId
    }


    fun insertarCargo(cargoData: CargoData): Long {
        val contentValues = ContentValues().apply {
            put("cargo", cargoData.cargo)
            put("sueldo", cargoData.sueldo)
            put("condicion", cargoData.condicion)
            put("servicio", cargoData.servicio)
        }

        val cargoId = db.insert("Cargo", null, contentValues)
        cargoData.idCargo = cargoId.toInt() // Asignar el valor del ID al objeto cargoData
        Log.d("Datos del usuarioData", cargoData.toString())

        return cargoId
    }
    fun insertarHorario(horarioData: HorarioData): Long {
        val contentValues = ContentValues().apply {
            put("diaSemana", horarioData.diaSemana)
            put("entrada", horarioData.entrada.toString())
            put("salida", horarioData.salida.toString())
        }

        val idHorario =  db.insert("Horario", null, contentValues)
        horarioData.idHorario = idHorario.toInt() // Asignar el valor del ID al objeto horarioData
        Log.d("Datos del usuarioData", horarioData.toString())

        return idHorario
    }

    fun saveUserData(userData: UserData, empleadoData: EmpleadoData): Long {
        return db.insert("User", null, userData.toContentValues())
    }

    fun saveEmpleadoData(empleadoData: EmpleadoData): Long {
        return db.insert("Empleado", null, empleadoData.toContentValues())
    }

    fun obtenerUsuariosHomeAdmin(): List<List<Any>> {
        val usuarios: MutableList<List<Any>> = mutableListOf()

        val query = "SELECT Empleado.idEmpleado, Usuario.idUser, Empleado.nombres, Empleado.telefono, Usuario.rol " +
                "FROM Usuario " +
                "JOIN Empleado ON Usuario.empleadoId = Empleado.idEmpleado"

        val cursor = db.rawQuery(query, null)

        cursor?.use {
            while (cursor.moveToNext()) {
                val idEmpleado = cursor.getInt(cursor.getColumnIndex("idEmpleado"))
                val idUser = cursor.getInt(cursor.getColumnIndex("idUser"))
                val nombre = cursor.getString(cursor.getColumnIndex("nombres"))
                val telefono = cursor.getString(cursor.getColumnIndex("telefono"))
                val rol = cursor.getInt(cursor.getColumnIndex("rol"))

                val usuario = listOf(idEmpleado, idUser, nombre, telefono, rol)
                usuarios.add(usuario)
            }
        }

        cursor?.close()
        //db.close()

        return usuarios
    }


    fun eliminarUsuarioPorId(id: Int): Int {
        return db.delete("Usuario", "idUser = ?", arrayOf(id.toString()))
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
