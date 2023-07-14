package com.cursoklotin.intento.models


data class BoletaData(
    var idBoleta: Int,
    var usuarioId: Int,
    var mes: String,
    var feriadoLaborado: Int,
    var descuentoTardanza: Double,
    var netoPagar: Double,
)