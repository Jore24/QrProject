package com.cursoklotin.intento.bd.services

import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.models.EmpleadoData


class EmployeQueryHelper(private val db: SQLiteDatabase) {
    fun getEmployeById(userId: Int): EmpleadoData? {

        val columns = arrayOf(
            "idEmpleado",
            "idHorario",
            "nombres",
            "sexo",
            "telefono",
            "dni",
            "numeroCuenta",
            "banco",
            "fechaNacimiento",
            "direccion",
            "distrito",
            "fechaCreacion",
            "ultimaActualizacion"
        )

        val selection = "idEmpleado = ?"
        val selectionArgs = arrayOf(userId.toString())

        val cursor = db.query("Empleado", columns, selection, selectionArgs, null, null, null)

        var empleadoData: EmpleadoData? = null

        if (cursor.moveToFirst()) {
            val idEmpleado = cursor.getInt(0)
            val idHorario = cursor.getInt(1)
            val nombres = cursor.getString(2)
            val sexo = cursor.getString(3)
            val telefono = cursor.getString(4)
            val dni = cursor.getString(5)
            val numeroCuenta = cursor.getString(6)
            val banco = cursor.getString(7)
            val fechaNacimiento = cursor.getString(8)
            val direccion = cursor.getString(9)
            val distrito = cursor.getString(10)
            val fechaCreacion = cursor.getString(11)
            val ultimaActualizacion = cursor.getString(12)

            empleadoData = EmpleadoData(
                idEmpleado,
                idHorario,
                nombres,
                sexo,
                telefono,
                dni,
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

        return empleadoData




}
}