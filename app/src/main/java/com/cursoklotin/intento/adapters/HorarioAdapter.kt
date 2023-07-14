package com.cursoklotin.intento.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursoklotin.intento.R
import com.cursoklotin.intento.models.HorarioData
import com.cursoklotin.intento.models.HorarioDataWithDias
import java.text.SimpleDateFormat
import java.util.*


class HorarioAdapter(private val listaHorario: List<HorarioData>, private val listaDias: List<Any>) :
    RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_horario, parent, false)
        return HorarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        val dias = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val dia = dias[position % 7] // Obtener el día correspondiente al índice actual

        val horarioData = listaHorario.find { it.diaSemana == dia }

        listaHorario.forEach {
            if (it.diaSemana != dia) {
                val entradaHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(it.entrada.time)
                val salidaHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(it.salida.time)

                holder.txtHoraEntrada.text = entradaHora
                holder.txtHoraSalida.text = salidaHora
                holder.txtEstado.text = "Laborable"
                return@forEach
            }
            else {
                holder.txtEstado.text = "Descanso"
            }
        }


        holder.txtDia.text = dia
    }

        override fun getItemCount(): Int {
            return listaDias.size
        }


        class HorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txtDia: TextView = itemView.findViewById(R.id.txtDia)
            val txtHoraEntrada: TextView = itemView.findViewById(R.id.txtHoraInicio)
            val txtHoraSalida: TextView = itemView.findViewById(R.id.txtHoraSalida)
            val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)

        }
    }