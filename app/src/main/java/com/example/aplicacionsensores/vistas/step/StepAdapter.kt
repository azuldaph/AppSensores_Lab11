package com.example.aplicacionsensores.vistas.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionsensores.model.Paso

class StepAdapter: RecyclerView.Adapter<StepViewHolder>() {

    private var stepList = emptyList<Paso>()

    fun setStep(pasos: List<Paso>){
        this.stepList = pasos
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = stepList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StepViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step:  Paso = stepList[position]
        holder.bind(step)
    }
}