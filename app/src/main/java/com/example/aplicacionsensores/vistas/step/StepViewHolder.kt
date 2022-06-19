package com.example.aplicacionsensores.vistas.step

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionsensores.R
import com.example.aplicacionsensores.model.Paso


class StepViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_step, parent, false)){

    private var textDate: TextView? = null
    private var textType: TextView? = null
    private var textStep: TextView? = null

    init {
        textDate = itemView.findViewById(R.id.textDate)
        textType = itemView.findViewById(R.id.textType)
        textStep = itemView.findViewById(R.id.textContador)
    }

    fun bind(paso: Paso){
        textDate?.text = paso.date
        textType?.text = paso.type
        textStep?.text = paso.step + "pasos"
    }

}