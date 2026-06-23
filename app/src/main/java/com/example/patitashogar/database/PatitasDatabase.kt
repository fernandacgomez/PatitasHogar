package com.example.patitashogar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DonacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PatitasDatabase : RoomDatabase() {

    abstract fun donacionDao(): DonacionDao

    companion object {

        @Volatile
        private var INSTANCE: PatitasDatabase? = null

        fun obtenerDatabase(context: Context): PatitasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasDatabase::class.java,
                    "patitas_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}