package com.cursoklotin.intento.bd.insertion

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.cursoklotin.intento.AsistenciaData
import com.cursoklotin.intento.QRData
import com.cursoklotin.intento.models.CargoData
import com.cursoklotin.intento.models.UserData
import com.cursoklotin.intento.models.EmpleadoData
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.utils.DateTimeUtils.getCurrentDate
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class DataInsertionHelper(private val db: SQLiteDatabase) {

    fun insertDefaultData() {
        insertCargoData()
        insertData()
        insertDefaultHorarioData()
        createAsistenciaTable()
        insertDefaultAsistenciaData()
        insertDefaultQRData()

    }

    private fun insertCargoData() {
        val cargos = arrayOf(
            CargoData(
                idCargo = -1,
                cargo = "Gerente",
                sueldo = 5000,
                condicion = "Full Time"
            ),
            CargoData(
                idCargo = -1,
                cargo = "Empleado",
                sueldo = 1205,
                condicion = "Part Time"
            ),
            CargoData(
                idCargo = -1,
                cargo = "Asistente",
                sueldo = 1551,
                condicion = "Full Time"
            )
        )

        for (cargo in cargos) {
            val values = ContentValues().apply {
                put("cargo", cargo.cargo)
                put("sueldo", cargo.sueldo)
                put("condicion", cargo.condicion)
            }

            db.insert("Cargo", null, values)
        }
    }

    private fun insertData() {
        val empleados = arrayOf(
            EmpleadoData(
                idEmpleado = -1,
                idHorario = -1,
                nombres = "John Doe",
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
                idEmpleado = -1,
                idHorario = -1,
                nombres = "Jane Smith",
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
                    idUser = -1,
                    correo = "jore@",
                    contrasena = "123",
                    rol = 1,
                    fechaInicio = "Fecha Inicio",
                    fechaFin = "Fecha Fin",
                    jefe = "Jefe A",
                    estadoCuenta = "Activo-Inactivo",
                    empleadoId = empleadoId.toInt(),
                    cargoId = 1,
                    url ="https://www.google.com"
                    // ID del cargo correspondiente
                ),
                UserData(
                    idUser = -1,
                    correo = "jane@",
                    contrasena = "123",
                    rol = 2,
                    fechaInicio = "Fecha Inicio",
                    fechaFin = "Fecha Fin",
                    jefe = "Jefe A",
                    estadoCuenta = "Activo-Inactivo",
                    empleadoId = empleadoId.toInt(),
                    cargoId = 2,
                    url ="https://www.google.com"

                    // ID del cargo correspondiente
                )
            )

            for (usuario in usuarios) {
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
                }

                db.insert("Usuario", null, values)
            }
        }
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

    val horaEntrada = Calendar.getInstance()
    val horaSalida = Calendar.getInstance()

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
            statement.bindString(2, horarioData.entrada.time.toString())
            statement.bindString(3, horarioData.salida.time.toString())

            statement.executeInsert()
        }
    }


    private fun createAsistenciaTable() {
        val query = "CREATE TABLE IF NOT EXISTS Asistencia (" +
                "idAsistencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idEmpleado INTEGER," +
                "idQR INTEGER," +
                "fecha TEXT," +
                "horaEntrada TEXT," +
                "horaSalida TEXT," +
                "estadoAsistencia INTEGER," +
                "FOREIGN KEY(idEmpleado) REFERENCES Empleado(idEmpleado)," +
                "FOREIGN KEY(idQR) REFERENCES QR(idQR)" +
                ")"
        db.execSQL(query)
    }

    private fun insertDefaultAsistenciaData() {
        val currentDate = getCurrentDate()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val asistenciaDataList = listOf(
            AsistenciaData(0, 1, 1, currentDate, horaEntrada, horaEntrada, 1),
            AsistenciaData(0, 2, 2, currentDate, horaEntrada, horaEntrada, 1),
            AsistenciaData(0, 3, 3, currentDate, horaEntrada, horaEntrada, 1)
        )

        for (asistenciaData in asistenciaDataList) {
            val query = "INSERT INTO Asistencia(idEmpleado, idQR, fecha, horaEntrada, horaSalida, estadoAsistencia) VALUES (?, ?, ?, ?, ?, ?)"
            val statement = db.compileStatement(query)

            statement.bindLong(1, asistenciaData.idEmpleado.toLong())
            statement.bindLong(2, asistenciaData.idQR.toLong())
            statement.bindString(3, dateFormat.format(asistenciaData.fecha.time))
            statement.bindLong(4, asistenciaData.horaEntrada.timeInMillis)
            statement.bindLong(5, asistenciaData.horaSalida.timeInMillis)
            statement.bindLong(6, asistenciaData.estadoAsistencia.toLong())

            statement.executeInsert()
        }
    }



    }



