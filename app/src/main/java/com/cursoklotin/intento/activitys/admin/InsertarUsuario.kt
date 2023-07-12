package com.cursoklotin.intento.activitys.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

class InsertarUsuario : AppCompatActivity() {

    private lateinit var txtNombres: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtCorreo: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var txtRol: EditText
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
    private lateinit var txtEstadoCuenta: EditText
    private lateinit var txtFechaInicio: EditText
    private lateinit var txtFechaFin: EditText
    private lateinit var txtSueldo: EditText
    private lateinit var txtDescansoSemanal: EditText
    private lateinit var txtEntrada: EditText
    private lateinit var txtSalida: EditText
    private lateinit var txtUrl: EditText
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
        txtEstadoCuenta = findViewById(R.id.txtEstadoCuenta)
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
            val rol = txtRol.text.toString()
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
            val estadoCuenta = txtEstadoCuenta.text.toString()
            val fechaInicio = txtFechaInicio.text.toString()
            val fechaFin = txtFechaFin.text.toString()
            val sueldo = txtSueldo.text.toString()
            val descansoSemanal = txtDescansoSemanal.text.toString()
            val horaEntradaString = txtEntrada.text.toString()
            val horaSalidaString = txtSalida.text.toString()
            val url = txtUrl.text.toString()

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
                estadoCuenta = estadoCuenta,
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

            val adminQueryHelper = AdminQueryHelper(this)

            val cargoId = adminQueryHelper.insertarCargo(cargoData)
            userData.cargoId = cargoId.toInt()

            horarioData.idHorario = adminQueryHelper.insertarHorario(horarioData).toInt()

            empleadoData.idHorario = horarioData.idHorario
            empleadoData.idEmpleado = adminQueryHelper.insertarEmpleado(empleadoData).toInt()

            userData.empleadoId = empleadoData.idEmpleado
            userData.cargoId = cargoId.toInt()
            adminQueryHelper.insertarUsuario(userData)

            //redirect con el intent
            if (userData != null) {
                val intent = Intent(this, HomeAdminActivity::class.java)
                startActivity(intent)
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



}
