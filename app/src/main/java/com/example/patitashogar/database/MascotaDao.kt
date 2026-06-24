package com.example.patitashogar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MascotaDao {

    @Insert
    suspend fun insertarMascota(mascota: MascotaEntity)

    @Query("SELECT * FROM mascotas ORDER BY id DESC")
    fun obtenerTodasLasMascotas(): Flow<List<MascotaEntity>>
}