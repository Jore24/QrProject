package com.cursoklotin.intento.models

data class UserData(
    var idUser: Int,
    var correo: String,
    var contrasena: String,
    var rol: Int,
    var fechaInicio: String,
    var fechaFin: String,
    var jefe: String,
    var estadoCuenta: String,
    var personaId: Int,
    var cargoId: Int = -1, // Valor predeterminado para cargoId
    var url: String
)