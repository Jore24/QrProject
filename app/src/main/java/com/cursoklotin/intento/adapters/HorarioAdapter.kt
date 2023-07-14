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


    class HorarioAdapter(private val listaHorario: List<HorarioData>, private val listaDias: List<Any>) :
        RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_horario, parent, false)
            return HorarioViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
            val dias = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
            val dia = dias[position % 7] // Obtener el día correspondiente al índice actual

            listaHorario.forEach {
                if (it.diaSemana != dia) {
                    holder.txtHoraEntrada.text = it.entrada.time.toString()
                    holder.txtHoraSalida.text = it.salida.time.toString()
                }
                else {
                    holder.txtHoraEntrada.text = "Dia de descanso"
                    holder.txtHoraSalida.text = "Dia de descanso"
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

        }
    }