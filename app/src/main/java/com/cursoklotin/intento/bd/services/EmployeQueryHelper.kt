package com.cursoklotin.intento.bd.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.AsistenciaData
import com.cursoklotin.intento.QRData
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

    //este funciona con id 1 y 2
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

                val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
                println("metodo "+entradaStr)
                println("metodo2 "+salidaStr)

                println(salidaStr)
                val entradaCalendar = Calendar.getInstance()
                val salidaCalendar = Calendar.getInstance()
                entradaCalendar.time = sdf.parse(entradaStr)
                salidaCalendar.time = sdf.parse(salidaStr)


                val horarioData = HorarioData(idHorario, diaSemana, entradaCalendar, salidaCalendar)
                horarios.add(horarioData)
                println("horarioData"+horarioData)
                println("salida entradaCalendar  "+entradaCalendar)
                println("salida salidaCalendar "+salidaCalendar)
            }
        }

        return horarios
    }
    //este debe funcionar con el
    fun getHorariosByIdUser3(empleadoId: Int): List<HorarioData> {
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
                val entradaCalendarStr = cursor.getString(cursor.getColumnIndex("entrada"))
                val salidaCalendarStr = cursor.getString(cursor.getColumnIndex("salida"))

                val entradaCalendar = Calendar.getInstance()
                entradaCalendar.timeInMillis = entradaCalendarStr.substringAfter("time=").substringBefore(",").toLong()

                val salidaCalendar = Calendar.getInstance()
                salidaCalendar.timeInMillis = salidaCalendarStr.substringAfter("time=").substringBefore(",").toLong()

                val horarioData = HorarioData(idHorario, diaSemana, entradaCalendar, salidaCalendar)
                horarios.add(horarioData)

                //println("entradaCalendar: $entradaCalendar")
                //println("salidaCalendar: $salidaCalendar")
            }
        }

        return horarios
    }

    fun getAsistenciasByIdEmpleado(empleadoId: Int): List<AsistenciaData> {
        val asistencias: MutableList<AsistenciaData> = mutableListOf()

        val query = "SELECT idAsistencia, idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia " +
                "FROM Asistencia " +
                "WHERE idEmpleado = ?"

        val cursor = db.rawQuery(query, arrayOf(empleadoId.toString()))

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        cursor?.use {
            while (cursor.moveToNext()) {
                val idAsistencia = cursor.getInt(cursor.getColumnIndex("idAsistencia"))
                val idEmpleado = cursor.getInt(cursor.getColumnIndex("idEmpleado"))
                val idQR = cursor.getInt(cursor.getColumnIndex("idQR"))
                val fechaStr = cursor.getString(cursor.getColumnIndex("fecha"))
                val horaEntradaStr = cursor.getString(cursor.getColumnIndex("horaEntrada"))
                val horaSalidaStr = cursor.getString(cursor.getColumnIndex("horaSalida"))
                val estadoAsistencia = cursor.getInt(cursor.getColumnIndex("estadoAsistencia"))

                val fecha = Calendar.getInstance()
                fecha.time = dateFormat.parse(fechaStr)

                val horaEntrada = Calendar.getInstance()
                horaEntrada.time = timeFormat.parse(horaEntradaStr)

                val horaSalida = Calendar.getInstance()
                horaSalida.time = timeFormat.parse(horaSalidaStr)

                val asistenciaData = AsistenciaData(idAsistencia, idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia)
                asistencias.add(asistenciaData)

                // Imprimir los datos de la asistencia
                println("ID Asistencia: ${asistenciaData.idAsistencia}")
                println("ID Empleado: ${asistenciaData.idEmpleado}")
                println("ID QR: ${asistenciaData.idQR}")
                println("Fecha: ${asistenciaData.fecha}")
                println("Hora de entrada: ${asistenciaData.horaEntrada}")
                println("Hora de salida: ${asistenciaData.horaSalida}")
                println("Estado de asistencia: ${asistenciaData.estadoAsistencia}")
                println("-----------------------")
            }
        }

        return asistencias
    }

    fun getNumeroAsistenciasByIdEmpleado(empleadoId: Int): Int {
        val query = "SELECT COUNT(*) AS count FROM Asistencia WHERE idEmpleado = ?"

        val cursor = db.rawQuery(query, arrayOf(empleadoId.toString()))

        var numeroAsistencias = 0

        cursor?.use {
            if (cursor.moveToFirst()) {
                numeroAsistencias = cursor.getInt(cursor.getColumnIndex("count"))
            }
        }

        return numeroAsistencias
    }

    fun getHorarioAsignadoByIdEmpleado(empleadoId: Int): HorarioData? {
        val query = "SELECT Horario.idHorario, Horario.diaSemana, Horario.entrada, Horario.salida " +
                "FROM Empleado " +
                "JOIN Horario ON Empleado.idHorario = Horario.idHorario " +
                "WHERE Empleado.idEmpleado = ?"

        val cursor = db.rawQuery(query, arrayOf(empleadoId.toString()))

        var horarioData: HorarioData? = null

        cursor?.use {
            if (cursor.moveToFirst()) {
                val idHorario = cursor.getInt(cursor.getColumnIndex("idHorario"))
                val diaSemana = cursor.getString(cursor.getColumnIndex("diaSemana"))
                val entradaCalendarStr = cursor.getString(cursor.getColumnIndex("entrada"))
                val salidaCalendarStr = cursor.getString(cursor.getColumnIndex("salida"))

                val entradaCalendar = Calendar.getInstance()
                entradaCalendar.timeInMillis = entradaCalendarStr.substringAfter("time=").substringBefore(",").toLong()

                val salidaCalendar = Calendar.getInstance()
                salidaCalendar.timeInMillis = salidaCalendarStr.substringAfter("time=").substringBefore(",").toLong()

                horarioData = HorarioData(idHorario, diaSemana, entradaCalendar, salidaCalendar)
                println(horarioData)
            }
        }

        return horarioData
    }

    fun getQr(): List<QRData> {
        val qrs: MutableList<QRData> = mutableListOf()

        val query = "SELECT id, codigo, estado FROM QR"

        val cursor = db.rawQuery(query, null)

        cursor?.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val codigo = cursor.getString(cursor.getColumnIndex("codigo"))
                val estado = cursor.getString(cursor.getColumnIndex("estado"))

                val qrData = QRData(id, codigo, estado)
                qrs.add(qrData)

                // Imprimir los datos del QR
                println("ID QR: $id, Código: $codigo, Estado: $estado")
            }
        }

        return qrs
    }

    fun marcarHoraEntradaAsistencia(
        idEmpleado: Int,
        idQR: Int,
        fecha: Calendar,
        horaEntrada: Calendar,
        horaSalida: Calendar,
        estadoAsistencia: Int
    ) {
        val query = "INSERT INTO Asistencia (idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia) " +
                "VALUES (?, ?, ?, ?, ?, ?)"

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) //cuidado
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()) //cuidado

        val fechaStr = dateFormat.format(fecha.time)
        val horaEntradaStr = timeFormat.format(horaEntrada.time)
        val horaSalidaStr = timeFormat.format(horaSalida.time)

        val values = arrayOf(idEmpleado, idQR, fechaStr, horaEntradaStr, horaSalidaStr, estadoAsistencia)

        db.execSQL(query, values)
    }




}