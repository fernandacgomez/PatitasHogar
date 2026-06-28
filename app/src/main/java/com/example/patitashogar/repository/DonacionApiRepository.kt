package com.example.patitashogar.repository

import com.example.patitashogar.service.DonacionApi
import com.example.patitashogar.service.RetrofitClient

class DonacionApiRepository {

    private val api = RetrofitClient.donacionApiService

    suspend fun obtenerDonaciones(): List<DonacionApi> {
        return api.obtenerDonaciones()
    }

    suspend fun guardarDonacion(donacion: DonacionApi): DonacionApi {
        return api.guardarDonacion(donacion)
    }
}