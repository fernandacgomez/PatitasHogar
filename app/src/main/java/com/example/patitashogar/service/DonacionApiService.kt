package com.example.patitashogar.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DonacionApiService {

    @GET("api/donaciones")
    suspend fun obtenerDonaciones(): List<DonacionApi>

    @POST("api/donaciones")
    suspend fun guardarDonacion(
        @Body donacion: DonacionApi
    ): DonacionApi
}