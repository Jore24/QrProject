package com.cursoklotin.intento.activitys.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cursoklotin.intento.managers.UserManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.adapters.HorarioAdapter
import com.cursoklotin.intento.bd.services.EmployeQueryHelper
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.models.HorarioDataWithDias
import java.text.SimpleDateFormat
import java.util.*

class HorarioActivity : AppCompatActivity() {
    private lateinit var recyclerViewHorario: RecyclerView
    private lateinit var employeQueryHelper: EmployeQueryHelper
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_horario)
        userManager = UserManager.getInstance(applicationContext)
        val empleadoData = userManager.empleadoData
        val empleadoId = empleadoData?.idEmpleado
        println("empleadoId: $empleadoId")
        employeQueryHelper = EmployeQueryHelper(this)
        val listHorarioResult = employeQueryHelper.getHorariosByIdUser3(empleadoId!!)
        val asistenciasEmpleadoId = employeQueryHelper.getAsistenciasByIdEmpleado(empleadoId)
        val getNumeroAsistenciasByIdEmpleado1 = employeQueryHelper.getNumeroAsistenciasByIdEmpleado(empleadoId)
        println("getNumeroAsistenciasByIdEmpleado1: $getNumeroAsistenciasByIdEmpleado1")
        val Qrs = employeQueryHelper.getQr()
        println("Qrs: $Qrs")
        Log.i("HorarioActivity", "asistenciasEmpleadoId: $asistenciasEmpleadoId")



        println("listHorarioResult: $listHorarioResult")

        recyclerViewHorario = findViewById(R.id.recyclerViewHorario)
        recyclerViewHorario.layoutManager = LinearLayoutManager(this)
        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

        recyclerViewHorario.adapter = HorarioAdapter(listHorarioResult, dias)



    }


}
