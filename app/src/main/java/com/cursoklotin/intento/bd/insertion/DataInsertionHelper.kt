package com.cursoklotin.intento.bd.insertion

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.cursoklotin.intento.AsistenciaData
import com.cursoklotin.intento.QRData
import com.cursoklotin.intento.models.*
import com.cursoklotin.intento.utils.DateTimeUtils.getCurrentDate
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class DataInsertionHelper(private val db: SQLiteDatabase) {

    fun insertDefaultData() {
        insertCargoData()
        insertDataEmpleados()
        insertDefaultHorarioData()
        insertDefaultAsistenciaData()
        insertDefaultQRData()
        insertBoletaData()

    }
    val horaEntrada = Calendar.getInstance()
    val horaSalida = Calendar.getInstance()

    private fun insertCargoData() {
        val cargos = arrayOf(
            CargoData(
                idCargo = -1,
                cargo = "Guardia",
                sueldo = 5000,
                condicion = "Full Time",
                servicio = "Seguridad"
            ),
            CargoData(
                idCargo = -1,
                cargo = "Agente",
                sueldo = 1205,
                condicion = "Part Time",
                servicio = "Atención al cliente"
            ),
            CargoData(
                idCargo = -1,
                cargo = "Asistente",
                sueldo = 1551,
                condicion = "Full Time",
                servicio = "Atención al cliente"
            )
        )

        for (cargo in cargos) {
            val values = ContentValues().apply {
                put("cargo", cargo.cargo)
                put("sueldo", cargo.sueldo)
                put("condicion", cargo.condicion)
                put("servicio", cargo.servicio)
            }

            db.insert("Cargo", null, values)
        }
    }

    private fun insertBoletaData() {
        val boletas = arrayOf(
            BoletaData(
                idBoleta = 1,
                usuarioId = 2,
                mes = "Enero",
                feriadoLaborado = 0,
                descuentoTardanza = 0.0,
                netoPagar = 0.1
            ),
            BoletaData(
                idBoleta = 2,
                usuarioId = 2,
                mes = "Febrero",
                feriadoLaborado = 0,
                descuentoTardanza = 0.0,
                netoPagar = 0.0
            )
        )

        for (boleta in boletas) {
            val values = ContentValues().apply {
                put("usuarioId", boleta.usuarioId)
                put("mes", boleta.mes)
                put("feriadoLaborado", boleta.feriadoLaborado)
                put("descuentoTardanza", boleta.descuentoTardanza)
                put("netoPagar", boleta.netoPagar)
            }

            db.insert("Boleta", null, values)

            // Imprimir los datos ingresados
            println("Datos ingresados:")
            println("ID Usuario: ${boleta.usuarioId}")
            println("Mes: ${boleta.mes}")
            println("Feriado Laborado: ${boleta.feriadoLaborado}")
            println("Descuento Tardanza: ${boleta.descuentoTardanza}")
            println("Neto a Pagar: ${boleta.netoPagar}")
            println("-------------------------")
        }
    }


//    private fun insertDefaultHorarioData() {
//        horaEntrada.set(Calendar.HOUR_OF_DAY, 9) // Hora de entrada a las 9:00 AM
//        horaEntrada.set(Calendar.MINUTE, 0)
//
//        horaSalida.set(Calendar.HOUR_OF_DAY, 18) // Hora de salida a las 6:00 PM
//        horaSalida.set(Calendar.MINUTE, 0)
//
//        val horarioDataList = listOf(
//            HorarioData(0, "Lunes", horaEntrada, horaSalida),
//            HorarioData(0, "Martes", horaEntrada, horaSalida),
//        )
//
//        for (horarioData in horarioDataList) {
//            val query = "INSERT INTO Horario(diaSemana, entrada, salida) VALUES (?, ?, ?)"
//            val statement = db.compileStatement(query)
//
//            statement.bindString(1, horarioData.diaSemana)
//            statement.bindString(2, horarioData.entrada.time.toString())
//            statement.bindString(3, horarioData.salida.time.toString())
//
//            statement.executeInsert()
//        }
//    }
    private fun insertDefaultHorarioData() {
        horaEntrada.set(Calendar.HOUR_OF_DAY, 9) // Hora de entrada a las 9:00 AM
        horaEntrada.set(Calendar.MINUTE, 0)

        horaSalida.set(Calendar.HOUR_OF_DAY, 18) // Hora de salida a las 6:00 PM
        horaSalida.set(Calendar.MINUTE, 0)

        val horarioDataList = listOf(
            HorarioData(0, "Lunes", horaEntrada, horaSalida),
            HorarioData(0, "Martes", horaEntrada, horaSalida),
        )

        for (horarioData in horarioDataList) {
            val query = "INSERT INTO Horario(diaSemana, entrada, salida) VALUES (?, ?, ?)"
            val statement = db.compileStatement(query)

            statement.bindString(1, horarioData.diaSemana)
            statement.bindLong(2, horarioData.entrada.timeInMillis)
            statement.bindLong(3, horarioData.salida.timeInMillis)

            statement.executeInsert()
        }
    }


    private fun insertDataEmpleados() {
        val empleados = arrayOf(
            EmpleadoData(
                idEmpleado = 1,
                idHorario = 1,
                nombres = "Jorge Ore",
                sexo = "Masculino",
                telefono = "987612588",
                dni = "76057972",
                numeroCuenta = "894144444441111122233",
                banco = "BCP",
                fechaNacimiento = "1990-01-01",
                direccion = "Dirección A",
                distrito = "Distrito A",
                fechaCreacion = "Fecha Creación",
                ultimaActualizacion = "Ultima Actualización"
            ),
            EmpleadoData(
                idEmpleado = 2,
                idHorario = 2,
                nombres = "Clarita Maria",
                sexo = "Femenino",
                telefono = "987612588",
                dni = "76057972",
                numeroCuenta = "894144444441111122233",
                banco = "BCP",
                fechaNacimiento = "1990-01-01",
                direccion = "Dirección A",
                distrito = "Distrito A",
                fechaCreacion = "Fecha Creación",
                ultimaActualizacion = "Ultima Actualización"
            )
        )

        for (empleado in empleados) {
            val values = ContentValues().apply {
                put("idHorario", empleado.idHorario)
                put("nombres", empleado.nombres)
                put("sexo", empleado.sexo)
                put("telefono", empleado.telefono)
                put("dni", empleado.dni)
                put("numeroCuenta", empleado.numeroCuenta)
                put("banco", empleado.banco)
                put("fechaNacimiento", empleado.fechaNacimiento)
                put("direccion", empleado.direccion)
                put("distrito", empleado.distrito)
                put("fechaCreacion", empleado.fechaCreacion)
                put("ultimaActualizacion", empleado.ultimaActualizacion)
            }

            val empleadoId = db.insert("Empleado", null, values)

            val usuarios = arrayOf(
                UserData(
                    idUser = 1,
                    correo = "jore@",
                    contrasena = "123",
                    rol = 2,
                    fechaInicio = "Fecha Inicio",
                    fechaFin = "Fecha Fin",
                    jefe = "Jefe A",
                    estadoCuenta = "Activo-Inactivo",
                    empleadoId = 1,
                    cargoId = 1,
                    url ="https://www.google.com"
                ),
                UserData(
                    idUser = 2,
                    correo = "jane@",
                    contrasena = "123",
                    rol = 1,
                    fechaInicio = "Fecha Inicio",
                    fechaFin = "Fecha Fin",
                    jefe = "Jefe A",
                    estadoCuenta = "Activo-Inactivo",
                    empleadoId = 2,
                    cargoId = 2,
                    url ="https://www.google.com"
                )
            )

            for (usuario in usuarios) {
                val correoExistente = checkExistingUsuario(usuario.correo)

                if (correoExistente) {
                    // El usuario ya existe, omitir la inserción
                    continue
                }

                values.clear()
                values.apply {
                    put("correo", usuario.correo)
                    put("contrasena", usuario.contrasena)
                    put("rol", usuario.rol)
                    put("fechaInicio", usuario.fechaInicio)
                    put("fechaFin", usuario.fechaFin)
                    put("jefe", usuario.jefe)
                    put("estadoCuenta", usuario.estadoCuenta)
                    put("empleadoId", usuario.empleadoId)
                    put("cargoId", usuario.cargoId)
                    put("url", usuario.url)
                }

                db.insert("Usuario", null, values)
            }
        }
    }

    private fun checkExistingUsuario(correo: String): Boolean {
        val query = "SELECT COUNT(*) FROM Usuario WHERE correo = ?"
        val cursor = db.rawQuery(query, arrayOf(correo))
        val count = if (cursor.moveToFirst()) cursor.getInt(0) else 0
        cursor.close()
        return count > 0
    }



    private fun insertDefaultQRData() {
        val qrDataList = listOf(
            QRData(1, "QR1", "Estado 1"),
            QRData(2, "QR2", "Estado 2"),
            QRData(3, "QR3", "Estado 3")
        )

        for (qrData in qrDataList) {
            val query = "INSERT INTO QR(id, codigo, estado) VALUES (?, ?, ?)"
            val statement = db.compileStatement(query)

            statement.bindLong(1, qrData.id.toLong())
            statement.bindString(2, qrData.codigo)
            statement.bindString(3, qrData.estado)

            statement.executeInsert()
        }
    }


//    private fun insertDefaultAsistenciaData() {
//        val currentDate = getCurrentDate()
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//
//        val horaEntrada1 = Calendar.getInstance() //declarar fuera esta instancia para que no se repita
//        horaEntrada1.set(Calendar.HOUR_OF_DAY, 12) // Establecer la hora de entrada a las 12:00
//        horaEntrada1.set(Calendar.MINUTE, 0)
//        horaEntrada1.set(Calendar.SECOND, 0)
//
//        val horaEntrada2 = Calendar.getInstance()
//        horaEntrada2.set(Calendar.HOUR_OF_DAY, 13) // Establecer la hora de entrada a las 13:00
//        horaEntrada2.set(Calendar.MINUTE, 0)
//        horaEntrada2.set(Calendar.SECOND, 0)
//
//        val horaSalida1 = Calendar.getInstance()
//        horaSalida1.set(Calendar.HOUR_OF_DAY, 16) // Establecer la hora de salida a las 16:00
//        horaSalida1.set(Calendar.MINUTE, 0)
//        horaSalida1.set(Calendar.SECOND, 0)
//
//        val horaSalida2 = Calendar.getInstance()
//        horaSalida2.set(Calendar.HOUR_OF_DAY, 17) // Establecer la hora de salida a las 17:00
//        horaSalida2.set(Calendar.MINUTE, 0)
//        horaSalida2.set(Calendar.SECOND, 0)
//
//        val asistenciaDataList = listOf(
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada1, horaSalida1, 1),
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida1, 1),
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
//            AsistenciaData(0, 2, 1, currentDate, horaEntrada1, horaSalida1, 1),
//            AsistenciaData(0, 2, 2, currentDate, horaEntrada2, horaSalida2, 1)
//        )
//        println("Lista Asistencia asistenciaDataList: $asistenciaDataList")
//
//        for (asistenciaData in asistenciaDataList) {
//            val query = "INSERT INTO Asistencia(idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia) VALUES (?, ?, ?, ?, ?, ?)"
//            val statement = db.compileStatement(query)
//
//            statement.bindLong(1, asistenciaData.idEmpleado.toLong())
//            statement.bindLong(2, asistenciaData.idQR.toLong())
//           // statement.bindString(3, dateFormat.format(asistenciaData.fecha.time))
////            statement.bindString(4, timeFormat.format(asistenciaData.horaEntrada.time))
////            statement.bindString(5, timeFormat.format(asistenciaData.horaSalida.time))
//            statement.bindString(3, asistenciaData.fecha.toString())
//            statement.bindString(4, asistenciaData.horaEntrada.toString())
//            statement.bindString(5, asistenciaData.horaSalida.toString())
//
//
//            statement.bindLong(6, asistenciaData.estadoAsistencia.toLong())
//
//            println("Lista Asistencia asistenciaData: AQUIIIIIIIIIIIIII $asistenciaData")
//
//
//            statement.executeInsert()
//
//            // Imprimir las horas de entrada y salida formateadas
//            println("Hora de entrada desde inserción : ${timeFormat.format(asistenciaData.horaEntrada.time)}")
//            println("Hora de salida: ${timeFormat.format(asistenciaData.horaSalida.time)}")
//            println("            println(asistenciaData)\n "+asistenciaData)
//        }
//    }

    private fun insertDefaultAsistenciaData() {
        val currentDate = getCurrentDate()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val horaEntrada1 = Calendar.getInstance()
        horaEntrada1.set(Calendar.HOUR_OF_DAY, 12)
        horaEntrada1.set(Calendar.MINUTE, 0)
        horaEntrada1.set(Calendar.SECOND, 0)

        val horaEntrada2 = Calendar.getInstance()
        horaEntrada2.set(Calendar.HOUR_OF_DAY, 13)
        horaEntrada2.set(Calendar.MINUTE, 0)
        horaEntrada2.set(Calendar.SECOND, 0)

        val horaSalida1 = Calendar.getInstance()
        horaSalida1.set(Calendar.HOUR_OF_DAY, 16)
        horaSalida1.set(Calendar.MINUTE, 0)
        horaSalida1.set(Calendar.SECOND, 0)

        val horaSalida2 = Calendar.getInstance()
        horaSalida2.set(Calendar.HOUR_OF_DAY, 17)
        horaSalida2.set(Calendar.MINUTE, 0)
        horaSalida2.set(Calendar.SECOND, 0)

        val asistenciaDataList = listOf(
            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
            AsistenciaData(0, 2, 1, currentDate, horaEntrada1, horaSalida1, 1),
            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida1, 1),
            AsistenciaData(0, 2, 1, currentDate, horaEntrada2, horaSalida2, 1),
            AsistenciaData(0, 2, 1, currentDate, horaEntrada1, horaSalida1, 1),
            AsistenciaData(0, 2, 2, currentDate, horaEntrada2, horaSalida2, 1)
        )
        println("Lista Asistencia asistenciaDataList: $asistenciaDataList")

        for (asistenciaData in asistenciaDataList) {
            val query = "INSERT INTO Asistencia(idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia) VALUES (?, ?, ?, ?, ?, ?)"
            val statement = db.compileStatement(query)

            statement.bindLong(1, asistenciaData.idEmpleado.toLong())
            statement.bindLong(2, asistenciaData.idQR.toLong())
            statement.bindString(3, dateFormat.format(asistenciaData.fecha.time))
            statement.bindLong(4, asistenciaData.horaEntrada.timeInMillis)
            statement.bindLong(5, asistenciaData.horaSalida.timeInMillis)
            statement.bindLong(6, asistenciaData.estadoAsistencia.toLong())

            println("Lista Asistencia asistenciaData: AQUIIIIIIIIIIIIII $asistenciaData")

            statement.executeInsert()

            // Imprimir las horas de entrada y salida formateadas
            val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
            println("Hora de entrada desde inserción : ${formatoHora.format(asistenciaData.horaEntrada.time)}")
            println("Hora de salida: ${formatoHora.format(asistenciaData.horaSalida.time)}")
            println("            println(asistenciaData)\n $asistenciaData")
        }
    }



}



