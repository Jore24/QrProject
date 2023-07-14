package com.cursoklotin.intento.activitys.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.adapters.HorarioAdapter
import com.cursoklotin.intento.bd.services.EmployeQueryHelper
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.models.HorarioDataWithDias
import java.util.*

class HorarioActivity : AppCompatActivity() {
    private lateinit var recyclerViewHorario: RecyclerView
    private lateinit var employeQueryHelper: EmployeQueryHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_horario)
        employeQueryHelper = EmployeQueryHelper(this)
        val listHorarioResult = employeQueryHelper.getHorariosByIdUser(3)
        println("listHorarioResult: $listHorarioResult")

        recyclerViewHorario = findViewById(R.id.recyclerViewHorario)
        recyclerViewHorario.layoutManager = LinearLayoutManager(this)
        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

        recyclerViewHorario.adapter = HorarioAdapter(listHorarioResult, dias)



}


}
