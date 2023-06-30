package com.cursoklotin.intento.models

data class EmpleadoData(
    val idEmpleado: Int,
    var nombres: String,
    var sexo: String,
    var telefono: String,
    var numeroCuenta: String,
    var banco: String,
    var fechaNacimiento: String,
    var direccion: String,
    var distrito: String,
    var fechaCreacion: String,
    var ultimaActualizacion: String
)