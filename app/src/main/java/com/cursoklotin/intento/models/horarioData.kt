package com.cursoklotin.intento.models
import java.time.LocalTime
import java.util.*

data class HorarioData(
    var idHorario: Int,
    var diaSemana: String,
    var entrada: Calendar,
    var salida: Calendar,
)
data class HorarioDataWithDias(val horarioData: HorarioData, val diasSemana: List<String>)


