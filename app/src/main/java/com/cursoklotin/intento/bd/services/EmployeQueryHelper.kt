package com.cursoklotin.intento.bd.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.bd.DatabaseHelper
import com.cursoklotin.intento.models.EmpleadoData
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.models.HorarioDataWithDias
import java.text.SimpleDateFormat
import java.util.Date

import java.util.*




class EmployeQueryHelper(private val context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper.getInstance(context)
    private val db: SQLiteDatabase = dbHelper.readableDatabase

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


    fun getHorariosByIdUser(empleadoId: Int): List<HorarioData> {
        val horarios: MutableList<HorarioData> = mutableListOf()

        val query = "SELECT Horario.idHorario, Horario.diaSemana, Horario.entrada, Horario.salida " +
                "FROM Empleado " +
                "JOIN Horario ON Empleado.idHorario = Horario.idHorario " +
                "WHERE Empleado.idEmpleado = ?"

        val cursor = db.rawQuery(query, arrayOf(empleadoId.toString()))

        cursor?.use {
            while (cursor.moveToNext()) {
                val idHorario = cursor.getInt(cursor.getColumnIndex("idHorario"))
                val diaSemana = cursor.getString(cursor.getColumnIndex("diaSemana"))
                val entradaStr = cursor.getString(cursor.getColumnIndex("entrada"))
                val salidaStr = cursor.getString(cursor.getColumnIndex("salida"))

                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val entradaCalendar = Calendar.getInstance()
                val salidaCalendar = Calendar.getInstance()
                entradaCalendar.time = sdf.parse(entradaStr)
                salidaCalendar.time = sdf.parse(salidaStr)

                val horarioData = HorarioData(idHorario, diaSemana, entradaCalendar, salidaCalendar)
                horarios.add(horarioData)
                println("horarioData"+horarioData)
            }
        }

        return horarios
    }






    private fun obtenerDiasSemana(diaSemana: String): List<String> {
        val dias = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val diasSemana: MutableList<String> = mutableListOf()

        for (i in 0 until diaSemana.length) {
            val char = diaSemana[i]
            if (char.isDigit()) {
                val index = char.toString().toInt()
                diasSemana.add(dias[index - 1])
            } else {
                // Agregar lógica para manejar caracteres no numéricos aquí
                // Por ejemplo, si el carácter es "M", agregar "Lunes" al lista de días
                if (char == 'M') {
                    diasSemana.add("Lunes")
                } else if (char == 'T') {
                    diasSemana.add("Martes")
                } else if (char == 'W') {
                    diasSemana.add("Miércoles")
                } else if (char == 'J') {
                    diasSemana.add("Jueves")
                } else if (char == 'F') {
                    diasSemana.add("Viernes")
                } else if (char == 'S') {
                    diasSemana.add("Sábado")
                } else if (char == 'D') {
                    diasSemana.add("Domingo")
                }
            }
        }

        return diasSemana
    }




}