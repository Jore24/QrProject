package com.cursoklotin.intento
import java.util.*

data class AsistenciaData(
    var idAsistencia: Int,
    var idEmpleado: Int,
    var idQR: Int,
    var fecha: Calendar,
    var horaEntrada: Calendar,
    var horaSalida: Calendar,
    var estadoAsistencia: Int,
)



