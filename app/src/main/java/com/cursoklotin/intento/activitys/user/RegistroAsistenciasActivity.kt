package com.cursoklotin.intento.activitys.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.adapters.HorarioAdapter
import com.cursoklotin.intento.adapters.RegistroAsistenciaAdapter
import com.cursoklotin.intento.bd.services.EmployeQueryHelper
import com.cursoklotin.intento.managers.UserManager

class RegistroAsistenciasActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var asistenciaAdapter: RegistroAsistenciaAdapter
    private lateinit var  userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)

        recyclerView = findViewById(R.id.recyclerViewAsistencia)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        userManager = UserManager.getInstance(applicationContext)
        val empleadoData = userManager.empleadoData
        val empleadoId = empleadoData?.idEmpleado

        //instanciar la clase que contiene los metodos de la bd
        val employeQueryHelper = EmployeQueryHelper(this)

        val asistenciasEmpleado = employeQueryHelper.getAsistenciasByIdEmpleado(empleadoId!!)
        val numeroAsistenciasEmpleado = employeQueryHelper.getNumeroAsistenciasByIdEmpleado(empleadoId)
        val diasAsistidos = asistenciasEmpleado.size

        val adapter = RegistroAsistenciaAdapter(asistenciasEmpleado, numeroAsistenciasEmpleado)
        recyclerView.adapter = adapter






    }




}




