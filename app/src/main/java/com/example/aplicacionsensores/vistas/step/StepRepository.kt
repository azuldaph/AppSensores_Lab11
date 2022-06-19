package com.example.aplicacionsensores.vistas.step

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.aplicacionsensores.database.TecsupRoomDatabase
import com.example.aplicacionsensores.model.Paso
import com.example.aplicacionsensores.model.dao.PasoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StepRepository(application: Application) {

    private val stepDao: PasoDao? = TecsupRoomDatabase.getInstance(application)?.pasoDao()

    fun getSteps(): LiveData<List<Paso>>{
        return stepDao?.getListStep()!!
    }

    suspend fun insertStepWithCorouties(step: Paso){
        processInsertStep(step)
    }

    private suspend fun processInsertStep(step: Paso){
        withContext(Dispatchers.Default){
            stepDao?.insert(step)
        }
    }
}

