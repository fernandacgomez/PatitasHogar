package com.example.patitashogar.repository

import com.example.patitashogar.database.DonacionDao
import com.example.patitashogar.database.DonacionEntity
import kotlinx.coroutines.flow.Flow

class DonacionRepository(
    private val donacionDao: DonacionDao
) {
    val todasLasDonaciones: Flow<List<DonacionEntity>> =
        donacionDao.obtenerTodasLasDonaciones()

    val donacionesEfectivo: Flow<List<DonacionEntity>> =
        donacionDao.obtenerDonacionesEfectivo()

    val donacionesInsumos: Flow<List<DonacionEntity>> =
        donacionDao.obtenerDonacionesInsumos()

    suspend fun insertarDonacion(donacion: DonacionEntity) {
        donacionDao.insertarDonacion(donacion)
    }
}