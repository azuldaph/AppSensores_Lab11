package com.example.aplicacionsensores.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.aplicacionsensores.model.Paso

@Dao
interface PasoDao {

    @Insert
    fun insert(paso: Paso)

    @Update
    fun update(paso: Paso)

    @Delete
    fun delete(paso: Paso)

    @Query("SELECT * FROM step_table ORDER BY date_step DESC")
    fun getListStep(): LiveData<List<Paso>>

}
