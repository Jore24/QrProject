package com.cursoklotin.intento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R

class UsuarioAdapter(private val listaUsuarios: List<List<Any>>) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var listener: UsuarioAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_usuarios, parent, false)
        return UsuarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        val nombre = usuario[2] as String
        val telefono = usuario[3] as String
        val rol = (usuario[4] as Int).toString()
        val idUsuario = usuario[1] as Int
        val idEmpleado = usuario[0] as Int

        holder.textViewNombre.text = nombre
        holder.textViewNumero.text = telefono
        holder.textViewRol.text = rol

        // Ver Boleta
        holder.btnBoleta.setOnClickListener {
            listener?.onVerDetallesBoleta(usuario)
        }

        // Editar
        holder.btnEditar.setOnClickListener {
            listener?.onEditar(usuario)
        }

        // Eliminar
        holder.btnEliminar.setOnClickListener {
            listener?.onEliminar(usuario)
        }



    }



    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    fun setListener(listener: UsuarioAdapterListener) {
        this.listener = listener
    }

    interface UsuarioAdapterListener {
        fun onVerDetallesBoleta(usuario: List<Any>)
        fun onEditar(usuario: List<Any>)
        fun onEliminar(usuario: List<Any>)
    }

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val textViewNumero: TextView = itemView.findViewById(R.id.textViewNumero)
        val textViewRol: TextView = itemView.findViewById(R.id.textViewRol)
        val btnBoleta: Button = itemView.findViewById(R.id.btnBoleta)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)
    }
}