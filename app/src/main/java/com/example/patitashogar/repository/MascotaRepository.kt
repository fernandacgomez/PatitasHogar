package com.example.patitashogar.repository

import com.example.patitashogar.database.MascotaDao
import com.example.patitashogar.database.MascotaEntity
import kotlinx.coroutines.flow.Flow

class MascotaRepository(
    private val mascotaDao: MascotaDao
) {
    val todasLasMascotas: Flow<List<MascotaEntity>> =
        mascotaDao.obtenerTodasLasMascotas()

    suspend fun insertarMascota(mascota: MascotaEntity) {
        mascotaDao.insertarMascota(mascota)
    }
}