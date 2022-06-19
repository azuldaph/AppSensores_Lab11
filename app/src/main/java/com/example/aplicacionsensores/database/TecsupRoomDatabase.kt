package com.example.aplicacionsensores.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplicacionsensores.model.Paso
import com.example.aplicacionsensores.model.dao.PasoDao

@Database(entities = [Paso::class], version = 1)
abstract class TecsupRoomDatabase : RoomDatabase() {

    abstract fun pasoDao(): PasoDao

    companion object{

        private const val DATABASE_NAME = "tecsup_database"

        @Volatile
        private var INSTANCE: TecsupRoomDatabase?= null

        fun getInstance(context: Context): TecsupRoomDatabase?{
            INSTANCE?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TecsupRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }

    }

}