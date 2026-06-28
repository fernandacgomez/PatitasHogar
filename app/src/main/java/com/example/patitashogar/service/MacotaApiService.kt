package com.example.patitashogar.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MascotaApiService {

    @GET("api/mascotas")
    suspend fun obtenerMascotas(): List<MascotaApi>

    @POST("api/mascotas")
    suspend fun guardarMascota(
        @Body mascota: MascotaApi
    ): MascotaApi
}