package com.example.aplicacionsensores.vistas.step

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionsensores.model.Paso
import kotlinx.coroutines.launch

class StepViewModel(application: Application): AndroidViewModel(application) {

    private val repository = StepRepository(application)

    val steps = repository.getSteps()

    fun saveStep(paso: Paso){
        viewModelScope.launch {
            repository.insertStepWithCorouties(paso)
        }
    }
}