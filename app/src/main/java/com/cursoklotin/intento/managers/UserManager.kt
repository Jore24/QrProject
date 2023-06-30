package com.cursoklotin.intento.managers
import android.content.Context
import android.content.SharedPreferences
import com.cursoklotin.intento.models.EmpleadoData


class UserManager private constructor(context: Context) {
    companion object {
        private const val PREF_NAME = "user_manager"
        private const val KEY_USER_ID = "user_id"

        private var instance: UserManager? = null

        fun getInstance(context: Context): UserManager {
            if (instance == null) {
                instance = UserManager(context.applicationContext)
            }
            return instance as UserManager
        }
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var empleadoId: Int
        get() = sharedPreferences.getInt(KEY_USER_ID, -1)
        set(value) {
            sharedPreferences.edit().putInt(KEY_USER_ID, value).apply()
        }

    var empleadoData: EmpleadoData?
        get() {
            val idEmpleado = sharedPreferences.getInt("idEmpleado", -1)
            val idHorario = sharedPreferences.getInt("idHorario", -1)
            val nombres = sharedPreferences.getString("nombres", "") ?: ""
            val sexo = sharedPreferences.getString("sexo", "") ?: ""
            val telefono = sharedPreferences.getString("telefono", "") ?: ""
            val dni = sharedPreferences.getString("dni", "") ?: ""
            val numeroCuenta = sharedPreferences.getString("numeroCuenta", "") ?: ""
            val banco = sharedPreferences.getString("banco", "") ?: ""
            val fechaNacimiento = sharedPreferences.getString("fechaNacimiento", "") ?: ""
            val direccion = sharedPreferences.getString("direccion", "") ?: ""
            val distrito = sharedPreferences.getString("distrito", "") ?: ""
            val fechaCreacion = sharedPreferences.getString("fechaCreacion", "") ?: ""
            val ultimaActualizacion = sharedPreferences.getString("ultimaActualizacion", "") ?: ""

            return if (idEmpleado != -1) {
                EmpleadoData(
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
            } else {
                null
            }
        }
        set(value) {
            if (value != null) {
                sharedPreferences.edit().apply {
                    putInt("idEmpleado", value.idEmpleado)
                    putInt("idHorario", value.idHorario)
                    putString("nombres", value.nombres)
                    putString("sexo", value.sexo)
                    putString("telefono", value.telefono)
                    putString("dni", value.dni)
                    putString("numeroCuenta", value.numeroCuenta)
                    putString("banco", value.banco)
                    putString("fechaNacimiento", value.fechaNacimiento)
                    putString("direccion", value.direccion)
                    putString("distrito", value.distrito)
                    putString("fechaCreacion", value.fechaCreacion)
                    putString("ultimaActualizacion", value.ultimaActualizacion)
                    apply()
                }
            } else {
                sharedPreferences.edit().clear().apply()
            }
        }

    fun isLoggedIn(): Boolean = empleadoId != -1

    fun updateEmpleadoData(nuevosNombres: String, nuevoTelefono: String) {
        val empleadoData = empleadoData ?: return

        // Actualizar los campos de nombres y teléfono
        empleadoData.nombres = nuevosNombres
        empleadoData.telefono = nuevoTelefono

        // Guardar los nuevos datos de empleado en el estado global
        this.empleadoData = empleadoData
    }

    fun resetEmpleadoData() {
        sharedPreferences.edit().clear().apply()
    }
}