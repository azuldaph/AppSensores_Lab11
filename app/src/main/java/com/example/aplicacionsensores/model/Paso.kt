package com.example.aplicacionsensores.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_table")
data class Paso (
    @ColumnInfo(name = "date_step")
    val date: String,

    @ColumnInfo(name = "type_step")
    val type: String,

    @ColumnInfo(name = "step_step")
    val step: String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_id")
    var stepID: Int = 0
}