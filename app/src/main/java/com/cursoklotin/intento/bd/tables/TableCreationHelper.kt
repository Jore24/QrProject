package com.cursoklotin.intento.bd.tables

import android.database.sqlite.SQLiteDatabase

class TableCreationHelper(private val db: SQLiteDatabase) {
    fun createTables() {
        createUserTable()
        createCargoTable()
        createEmpleadoTable()
        createScheduleTable()
        createAttendanceTable()
        createQRTable()
    }

    private fun createCargoTable() {
        val query = "CREATE TABLE IF NOT EXISTS Cargo (" +
                "idCargo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cargo TEXT," +
                "sueldo INTEGER," +
                "condicion TEXT" +
                ")"
        db.execSQL(query)
    }

    private fun createEmpleadoTable() {
        val query = "CREATE TABLE IF NOT EXISTS Empleado (" +
                "idEmpleado INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idHorario INTEGER," +
                "nombres TEXT," +
                "sexo TEXT," +
                "telefono TEXT," +
                "dni TEXT," +
                "numeroCuenta TEXT," +
                "banco TEXT," +
                "fechaNacimiento TEXT," +
                "direccion TEXT," +
                "distrito TEXT," +
                "fechaCreacion TEXT," +
                "ultimaActualizacion TEXT," +
                "FOREIGN KEY(idHorario) REFERENCES Horario(idHorario)" +
                ")"
        db.execSQL(query)
    }


    private fun createUserTable() {
        val query = "CREATE TABLE IF NOT EXISTS Usuario (" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "correo TEXT," +
                "contrasena TEXT," +
                "rol INTEGER," +
                "fechaInicio TEXT," +
                "fechaFin TEXT," +
                "jefe TEXT," +
                "estadoCuenta TEXT," +
                "empleadoId INTEGER," +
                "cargoId INTEGER," +
                "url TEXT," +
                "FOREIGN KEY(empleadoId) REFERENCES Empleado(idEmpleado)," +
                "FOREIGN KEY(cargoId) REFERENCES Cargo(idCargo)" +
                ")"
        db.execSQL(query)
    }

    private fun createScheduleTable() {
        val query = "CREATE TABLE IF NOT EXISTS Horario (" +
                "idHorario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "diaSemana TEXT," +
                "entrada TEXT," +
                "salida TEXT," +
                "empleadoId INTEGER," +
                "FOREIGN KEY(empleadoId) REFERENCES Empleado(idEmpleado)" +
                ")"
        db.execSQL(query)
    }

    private fun createAttendanceTable() {
        val query = "CREATE TABLE IF NOT EXISTS Asistencia (" +
                "idAsistencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idEmpleado INTEGER," +
                "idQR INTEGER," +
                "fecha TEXT," +
                "horaEntrada TEXT," +
                "horaSalida TEXT," +
                "estadoAsistencia INTEGER," +
                "FOREIGN KEY(idEmpleado) REFERENCES Empleado(idEmpleado)," +
                "FOREIGN KEY(idQR) REFERENCES QR(id)" +
                ")"
        db.execSQL(query)
    }

    private fun createQRTable() {
        val query = "CREATE TABLE IF NOT EXISTS QR (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo TEXT," +
                "estado TEXT" +
                ")"
        db.execSQL(query)
    }
}
