package com.example.patitashogar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [DonacionEntity::class, MascotaEntity::class],
    version = 2,
    exportSchema = false
)
abstract class PatitasDatabase : RoomDatabase() {

    abstract fun donacionDao(): DonacionDao
    abstract fun mascotaDao(): MascotaDao

    companion object {

        @Volatile
        private var INSTANCE: PatitasDatabase? = null

        // Migración de versión 1 a 2: crea la tabla mascotas
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS mascotas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        nombre TEXT NOT NULL DEFAULT '',
                        especie TEXT NOT NULL DEFAULT '',
                        raza TEXT NOT NULL DEFAULT '',
                        edad TEXT NOT NULL DEFAULT '',
                        descripcion TEXT NOT NULL DEFAULT '',
                        estado TEXT NOT NULL DEFAULT 'Disponible',
                        nombreContacto TEXT NOT NULL DEFAULT '',
                        telefonoContacto TEXT NOT NULL DEFAULT '',
                        fotoUri TEXT NOT NULL DEFAULT '',
                        fechaRegistro TEXT NOT NULL DEFAULT ''
                    )
                    """.trimIndent()
                )
            }
        }

        fun obtenerDatabase(context: Context): PatitasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasDatabase::class.java,
                    "patitas_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}