package com.cursoklotin.intento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.AsistenciaData
import com.cursoklotin.intento.R
import java.text.SimpleDateFormat
import java.util.*


class RegistroAsistenciaAdapter(private val asistencias: List<AsistenciaData>, private val diasAsistidos: Int) : RecyclerView.Adapter<RegistroAsistenciaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_asistencia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asistencia = asistencias[position]

        // Configurar la vista con los datos de la asistencia
        holder.bind(asistencia)

        // Actualizar el estado de días asistidos
        holder.diasAsistidos.text = diasAsistidos.toString()
    }

    override fun getItemCount(): Int {
        return asistencias.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Enlazar los elementos de la vista aquí
        val diasAsistidos: TextView = itemView.findViewById(R.id.txtEstado)
        val txtFecha: TextView = itemView.findViewById(R.id.txtFecha)
        val txtHora: TextView = itemView.findViewById(R.id.txtHora)

        fun bind(asistencia: AsistenciaData) {
            // Actualizar la vista con los datos de la asistencia
            val fecha = asistencia.fecha
            val formattedFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(fecha.time)

            val horaEntrada = asistencia.horaEntrada
            val formattedHoraEntrada = SimpleDateFormat("HH:mm", Locale.getDefault()).format(horaEntrada.time)

            val horaSalida = asistencia.horaSalida
            val formattedHoraSalida = SimpleDateFormat("HH:mm", Locale.getDefault()).format(horaSalida.time)

            // Calcular la diferencia de tiempo entre la hora de entrada y la hora de salida
            val diffInMillis = horaSalida.timeInMillis - horaEntrada.timeInMillis
            val horasTrabajadas = diffInMillis / (1000 * 60 * 60)
            val minutosTrabajados = (diffInMillis % (1000 * 60 * 60)) / (1000 * 60)

            txtFecha.text = formattedFecha
            txtHora.text = "Entrada: $formattedHoraEntrada - Salida: $formattedHoraSalida\nHoras trabajadas: $horasTrabajadas horas $minutosTrabajados minutos"
            diasAsistidos.visibility = View.GONE


            // Aquí puedes configurar los demás elementos de la vista según los datos de la asistencia
        }


    }
}


