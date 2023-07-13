package com.cursoklotin.intento.activitys.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R
import com.cursoklotin.intento.bd.services.AdminQueryHelper
import com.cursoklotin.intento.models.CargoData
import com.cursoklotin.intento.models.EmpleadoData
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.models.UserData
import com.cursoklotin.intento.utils.TimePickerUtils
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.widget.AdapterView.OnItemSelectedListener;

class InsertarUsuario : AppCompatActivity() {

    private lateinit var txtNombres: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtCorreo: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var txtRol: Spinner
    private lateinit var txtCondicion: EditText
    private lateinit var txtBancoSueldo: EditText
    private lateinit var txtNumeroCuenta: EditText
    private lateinit var txtDni: EditText
    private lateinit var txtCargo: EditText
    private lateinit var txtFechaNacimiento: EditText
    private lateinit var txtSexo: EditText
    private lateinit var txtServicio: EditText
    private lateinit var txtJefe: EditText
    private lateinit var txtDistrito: EditText
    private lateinit var txtDireccion: EditText

    //private lateinit var txtEstadoCuenta: EditText
    private lateinit var txtFechaInicio: EditText
    private lateinit var txtFechaFin: EditText
    private lateinit var txtSueldo: EditText
    private lateinit var txtDescansoSemanal: EditText
    private lateinit var txtEntrada: EditText
    private lateinit var txtSalida: EditText
    private lateinit var txtUrl: ImageButton
    private lateinit var btnGuardar: Button
    //private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_insertar_usuario)

        txtNombres = findViewById(R.id.txtNombres)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtContrasena = findViewById(R.id.txtContrasena)
        txtRol = findViewById(R.id.txtRol)
        txtCondicion = findViewById(R.id.txtCondicion)
        txtBancoSueldo = findViewById(R.id.txtBancoSueldo)
        txtNumeroCuenta = findViewById(R.id.txtNumeroCuenta)
        txtDni = findViewById(R.id.txtDni)
        txtCargo = findViewById(R.id.txtCargo)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        txtSexo = findViewById(R.id.txtSexo)
        txtServicio = findViewById(R.id.txtServicio)
        txtJefe = findViewById(R.id.txtJefe)
        txtDistrito = findViewById(R.id.txtDistrito)
        txtDireccion = findViewById(R.id.txtDireccion)
        //txtEstadoCuenta = findViewById(R.id.txtEstadoCuenta)
        txtFechaInicio = findViewById(R.id.txtFechaInicio)
        txtFechaFin = findViewById(R.id.txtFechaFin)
        txtSueldo = findViewById(R.id.txtSueldo)
        txtDescansoSemanal = findViewById(R.id.txtDescansoSemanal)
        txtEntrada = findViewById(R.id.txtEntrada)
        txtSalida = findViewById(R.id.txtSalida)
        txtUrl = findViewById(R.id.txtUrl)
        btnGuardar = findViewById(R.id.btnGuardar)

        disableEditTextInteraction(txtFechaFin)
        disableEditTextInteraction(txtEntrada)
        disableEditTextInteraction(txtSalida)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.roles_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        txtRol.adapter = adapter

        txtRol.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedRole = parent.getItemAtPosition(position).toString()
                // Realiza las acciones necesarias con el rol seleccionado
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acciones cuando no se selecciona ningún elemento del Spinner
            }
        }

        txtFechaNacimiento.isFocusable = false
        txtFechaNacimiento.isFocusableInTouchMode = false
        txtFechaNacimiento.setOnClickListener(null)

        txtFechaNacimiento.setOnClickListener {
            mostrarDatePicker(txtFechaNacimiento)
        }
        txtFechaInicio.setOnClickListener {
            mostrarDatePicker(txtFechaInicio)
        }

        txtFechaFin.setOnClickListener {
            mostrarDatePicker(txtFechaFin)
        }

        txtEntrada.setOnClickListener {
            TimePickerUtils.showTimePickerDialog(this, txtEntrada)
        }

        txtSalida.setOnClickListener {
            TimePickerUtils.showTimePickerDialog(this, txtSalida)
        }

        btnGuardar.setOnClickListener {
            val nombres = txtNombres.text.toString()
            val telefono = txtTelefono.text.toString()
            val correo = txtCorreo.text.toString()
            val contrasena = txtContrasena.text.toString()
            val rol = txtRol.selectedItem.toString()
            val condicion = txtCondicion.text.toString()
            val bancoSueldo = txtBancoSueldo.text.toString()
            val numeroCuenta = txtNumeroCuenta.text.toString()
            val dni = txtDni.text.toString()
            val cargo = txtCargo.text.toString()
            val fechaNacimiento = txtFechaNacimiento.text.toString()
            val sexo = txtSexo.text.toString()
            val servicio = txtServicio.text.toString()
            val jefe = txtJefe.text.toString()
            val distrito = txtDistrito.text.toString()
            val direccion = txtDireccion.text.toString()
            //val estadoCuenta = txtEstadoCuenta.text.toString()
            val fechaInicio = txtFechaInicio.text.toString()
            val fechaFin = txtFechaFin.text.toString()
            val sueldo = txtSueldo.text.toString()
            val descansoSemanal = txtDescansoSemanal.text.toString()
            val horaEntradaString = txtEntrada.text.toString()
            val horaSalidaString = txtSalida.text.toString()
            val url = txtUrl.toString()

            //convertir a calendar
            val entradaCalendar = obtenerCalendarDesdeHoraString(horaEntradaString)
            val salidaCalendar = obtenerCalendarDesdeHoraString(horaSalidaString)

            val userData = UserData(
                idUser = 0, //aquí creo un usuario nuevo
                correo = correo,
                contrasena = contrasena,
                rol = rol.toInt(),
                fechaInicio = fechaInicio,
                fechaFin = fechaFin,
                jefe = jefe,
                estadoCuenta = "Activo",
                empleadoId = 0,//aquí creo un horario nuevo para el empleado
                cargoId = 0,//aquí creo un horario nuevo para el empleado
                url = url
            )

            val horarioData = HorarioData(
                idHorario = 0, //aquí creo un horario nuevo para el empleado
                diaSemana = descansoSemanal,
                entrada = entradaCalendar,
                salida = salidaCalendar
            )

            val empleadoData = EmpleadoData(
                idEmpleado = 0, //aquí creo un empleado nuevo
                idHorario = 0, //aquí creo un horario nuevo para el empleado
                nombres = nombres,
                sexo = sexo,
                telefono = telefono,
                dni = dni,
                numeroCuenta = numeroCuenta,
                banco = bancoSueldo,
                fechaNacimiento = fechaNacimiento,
                direccion = direccion,
                distrito = distrito,
                fechaCreacion = "Hoy",
                ultimaActualizacion = "Hoy"
            )

            val cargoData = CargoData(
                idCargo = 0, //aquí creo un cargo nuevo
                cargo = cargo,
                sueldo = sueldo.toInt(),
                condicion = condicion,
                servicio = servicio
            )

            if (validarTelefono(telefono) && (validarCorreo(correo)) && (validarDni(dni)) ) {
                val adminQueryHelper = AdminQueryHelper(this)

                val cargoId = adminQueryHelper.insertarCargo(cargoData)
                userData.cargoId = cargoId.toInt()

                horarioData.idHorario = adminQueryHelper.insertarHorario(horarioData).toInt()

                empleadoData.idHorario = horarioData.idHorario
                empleadoData.idEmpleado = adminQueryHelper.insertarEmpleado(empleadoData).toInt()

                userData.empleadoId = empleadoData.idEmpleado
                userData.cargoId = cargoId.toInt()
                adminQueryHelper.insertarUsuario(userData)

                if (userData != null) {
                    val intent = Intent(this, HomeAdminActivity::class.java)
                    startActivity(intent)
                }
            }
            else {
            // El teléfono no es válido, realiza la acción correspondiente
        }


            //println("Entrada: ${horarioData.entrada.get(Calendar.HOUR_OF_DAY)}:${horarioData.entrada.get(Calendar.MINUTE)}")
            //println("Salida: ${horarioData.salida.get(Calendar.HOUR_OF_DAY)}:${horarioData.salida.get(Calendar.MINUTE)}")

        }
    }

    private fun mostrarDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.calendarViewShown = false
        datePickerDialog.show()
    }

    private fun obtenerCalendarDesdeHoraString(horaString: String): Calendar {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val hora = dateFormat.parse(horaString)

        val calendar = Calendar.getInstance()
        calendar.time = hora

        return calendar
    }

    private fun disableEditTextInteraction(editText: EditText) {
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.setOnClickListener(null)
    }

    private fun validarTelefono(telefono: String): Boolean {
        // Validación: Solo números
        val soloNumeros = telefono.matches(Regex("\\d+"))
        if (!soloNumeros) {
            Toast.makeText(this, "El teléfono debe contener solo números", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        // Validación: No es una cadena de texto vacía
        if (telefono.isEmpty()) {
            Toast.makeText(this, "El teléfono no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: Número positivo
        val esNumeroPositivo = telefono.toIntOrNull()?.let { it > 0 } ?: false
        if (!esNumeroPositivo) {
            Toast.makeText(this, "El teléfono debe ser un número positivo", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        // Validación: Comienza con el número 9 (para Perú)
        val comienzaCon9 = telefono.startsWith("9")
        if (!comienzaCon9) {
            Toast.makeText(this, "El teléfono debe comenzar con el número 9", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        // Validación: No son ceros repetidos
        val noSonCerosRepetidos = !telefono.matches(Regex("^(0+)$"))
        if (!noSonCerosRepetidos) {
            Toast.makeText(
                this,
                "El teléfono no puede ser una cadena de ceros repetidos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: Tiene 9 dígitos
        val tiene9Digitos = telefono.length == 9
        if (!tiene9Digitos) {
            Toast.makeText(this, "El teléfono debe tener 9 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        // Todas las validaciones pasaron, el teléfono es válido
        return true
    }

    private fun validarCorreo(correo: String): Boolean {
        // Validación: No es una cadena de texto vacía
        if (correo.isEmpty()) {
            Toast.makeText(this, "El correo no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: Tiene un @
        val tieneArroba = correo.contains("@")
        if (!tieneArroba) {
            Toast.makeText(this, "El correo debe contener un @", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: Tiene un punto
        val tienePunto = correo.contains(".")
        if (!tienePunto) {
            Toast.makeText(this, "El correo debe contener un punto", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: No tiene espacios
        val noTieneEspacios = !correo.contains(" ")
        if (!noTieneEspacios) {
            Toast.makeText(this, "El correo no puede contener espacios", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: No tiene caracteres especiales
        val noTieneCaracteresEspeciales = correo.matches(Regex("^[a-zA-Z0-9@.]+$"))
        if (!noTieneCaracteresEspeciales) {
            Toast.makeText(
                this,
                "El correo no puede contener caracteres especiales",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No tiene dos puntos seguidos
        val noTieneDosPuntosSeguidos = !correo.contains("..")
        if (!noTieneDosPuntosSeguidos) {
            Toast.makeText(
                this,
                "El correo no puede contener dos puntos seguidos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No tiene dos arrobas seguidas
        val noTieneDosArrobasSeguidas = !correo.contains("@@")
        if (!noTieneDosArrobasSeguidas) {
            Toast.makeText(
                this,
                "El correo no puede contener dos arrobas seguidas",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No tiene dos puntos antes de la arroba
        val noTieneDosPuntosAntesDeLaArroba = !correo.contains("..@")
        if (!noTieneDosPuntosAntesDeLaArroba) {
            Toast.makeText(
                this,
                "El correo no puede contener dos puntos antes de la arroba",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

    return true
    }

    private fun validarContrasena(contrasena: String): Boolean {
        // Validación: No es una cadena de texto vacía
        if (contrasena.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: Tiene al menos 8 caracteres
        val tieneAlMenos8Caracteres = contrasena.length >= 8
        if (!tieneAlMenos8Caracteres) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos 8 caracteres",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: Tiene al menos una letra mayúscula
        val tieneAlMenosUnaLetraMayuscula = contrasena.matches(Regex(".*[A-Z].*"))
        if (!tieneAlMenosUnaLetraMayuscula) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos una letra mayúscula",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: Tiene al menos una letra minúscula
        val tieneAlMenosUnaLetraMinuscula = contrasena.matches(Regex(".*[a-z].*"))
        if (!tieneAlMenosUnaLetraMinuscula) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos una letra minúscula",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: Tiene al menos un número
        val tieneAlMenosUnNumero = contrasena.matches(Regex(".*\\d.*"))
        if (!tieneAlMenosUnNumero) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos un número",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: Tiene al menos un caracter especial
        val tieneAlMenosUnCaracterEspecial = contrasena.matches(Regex(".*[!@#\$%^&*+=?-].*"))
        if (!tieneAlMenosUnCaracterEspecial) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos un caracter especial",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun validarDni(dni: String): Boolean {
        // Validación: No es una cadena de texto vacía
        if (dni.isEmpty()) {
            Toast.makeText(this, "El DNI no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: Tiene 8 dígitos
        val tiene8Digitos = dni.length == 8
        if (!tiene8Digitos) {
            Toast.makeText(this, "El DNI debe tener 8 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación: No son ceros repetidos
        val noSonCerosRepetidos = !dni.matches(Regex("^(0+)$"))
        if (!noSonCerosRepetidos) {
            Toast.makeText(
                this,
                "El DNI no puede ser una cadena de ceros repetidos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No son números repetidos
        val noSonNumerosRepetidos = !dni.matches(Regex("^(1+)$"))
        if (!noSonNumerosRepetidos) {
            Toast.makeText(
                this,
                "El DNI no puede ser una cadena de números repetidos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No son números consecutivos
        val noSonNumerosConsecutivos = !dni.matches(Regex("^(12345678|23456789|34567890)$"))
        if (!noSonNumerosConsecutivos) {
            Toast.makeText(
                this,
                "El DNI no puede ser una cadena de números consecutivos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No son números consecutivos inversos
        val noSonNumerosConsecutivosInversos =
            !dni.matches(Regex("^(87654321|98765432|09876543)$"))
        if (!noSonNumerosConsecutivosInversos) {
            Toast.makeText(
                this,
                "El DNI no puede ser una cadena de números consecutivos inversos",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Validación: No son números consecutivos iguales
        val noSonNumerosConsecutivosIguales =
            !dni.matches(Regex("^(11111111|22222222|33333333|44444444|55555555|66666666|77777777|88888888|99999999)$"))
        if (!noSonNumerosConsecutivosIguales) {
            Toast.makeText(
                this,
                "El DNI no puede ser una cadena de números consecutivos iguales",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}
