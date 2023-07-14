package com.cursoklotin.intento.activitys.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.adapters.HorarioAdapter
import com.cursoklotin.intento.adapters.RegistroAsistenciaAdapter
import com.cursoklotin.intento.bd.services.EmployeQueryHelper

class RegistroAsistenciasActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var asistenciaAdapter: RegistroAsistenciaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)

        recyclerView = findViewById(R.id.recyclerViewAsistencia)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //instanciar la clase que contiene los metodos de la bd
        val employeQueryHelper = EmployeQueryHelper(this)

        val asistenciasEmpleado = employeQueryHelper.getAsistenciasByIdEmpleado(1)
        val numeroAsistenciasEmpleado = employeQueryHelper.getNumeroAsistenciasByIdEmpleado(1)
        val diasAsistidos = asistenciasEmpleado.size

        val adapter = RegistroAsistenciaAdapter(asistenciasEmpleado, numeroAsistenciasEmpleado)
        recyclerView.adapter = adapter






    }




}




