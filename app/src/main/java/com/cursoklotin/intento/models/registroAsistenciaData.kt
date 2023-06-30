package com.cursoklotin.intento

data class RegistroAsistenciaData(
    val id: Int,
    val fecha: String,
    val hora_entrada: String,
    val hora_salida: String,
    val llegada_tarde: Float,
    val empleado_id: Int,
    val codigo_id: Int
)



