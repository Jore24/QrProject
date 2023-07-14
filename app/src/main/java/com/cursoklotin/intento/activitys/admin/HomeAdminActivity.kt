package com.cursoklotin.intento.activitys.admin
import android.content.Intent
import com.cursoklotin.intento.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.activitys.auth.LoginActivity
import com.cursoklotin.intento.adapters.UsuarioAdapter
import com.cursoklotin.intento.models.UserData
import com.cursoklotin.intento.bd.services.AdminQueryHelper


class HomeAdminActivity : AppCompatActivity(), UsuarioAdapter.UsuarioAdapterListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsuarioAdapter
    private lateinit var adminQueryHelper: AdminQueryHelper
    private lateinit var btnInsertar: ImageButton
    private lateinit var btnPerfil: ImageButton
    private lateinit var btnAtras: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_home)

        adminQueryHelper = AdminQueryHelper(this)
        recyclerView = findViewById(R.id.recyclerViewUsuarios)
        btnInsertar = findViewById(R.id.btnInsertar)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnAtras = findViewById(R.id.btnAtras)

        //corregir este botón por que este debe cerrar la sesión por que no puede retroceder al login
        btnAtras.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnPerfil.setOnClickListener(){
            //metodo para obtener el idEmpleado y idUsuario del administrador
            val intent = Intent(this, ProfileAdminActivity::class.java)
            startActivity(intent)
        }

        val usuarios = adminQueryHelper.obtenerUsuariosHomeAdmin()

        adapter = UsuarioAdapter(usuarios)
        adapter.setListener(this)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        btnInsertar.setOnClickListener(){
            val intent = Intent(this, InsertarUsuario::class.java)
            startActivity(intent)
        }
    }

    override fun onVerDetallesBoleta(usuario: List<Any>) {
        // Obtener el ID del usuario y del empleado
        val idUsuario = usuario[1] as Int
        val idEmpleado = usuario[0] as Int

        val mensaje = "Ver detalles de la boleta del usuario con ID: $idUsuario y ID de empleado: $idEmpleado"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }



    override fun onEditar(usuario: List<Any>) {
        // Obtener el ID del usuario y del empleado
        val idUsuario = usuario[1] as Int
        val idEmpleado = usuario[0] as Int

        val mensaje = "Ver detalles de la boleta del usuario con ID: $idUsuario y ID de empleado: $idEmpleado"
        //intent
        intent = Intent(this, EditarUserActivity::class.java)
        intent.putExtra("idUsuario", idUsuario)
        intent.putExtra("idEmpleado", idEmpleado)
        startActivity(intent)

        //Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    //funcion para eliminar
    override fun onEliminar(usuario: List<Any>) {
        val idUsuario = usuario[1] as Int
        val idEmpleado = usuario[0] as Int
        adminQueryHelper.eliminarUsuarioPorId(idUsuario)
        val mensaje = "Eliminar usuario con ID: $idUsuario y ID de empleado: $idEmpleado"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        recreate() // Recargar la página
    }



}



