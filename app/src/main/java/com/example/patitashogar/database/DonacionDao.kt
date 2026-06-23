package com.example.patitashogar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DonacionDao {

    @Insert
    suspend fun insertarDonacion(donacion: DonacionEntity)

    @Query("SELECT * FROM donaciones ORDER BY id DESC")
    fun obtenerTodasLasDonaciones(): Flow<List<DonacionEntity>>

    @Query("SELECT * FROM donaciones WHERE tipoDonacion = 'Efectivo' ORDER BY id DESC")
    fun obtenerDonacionesEfectivo(): Flow<List<DonacionEntity>>

    @Query("SELECT * FROM donaciones WHERE tipoDonacion = 'Insumo' ORDER BY id DESC")
    fun obtenerDonacionesInsumos(): Flow<List<DonacionEntity>>
}
