package com.example.patitashogar.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioApiService {

    @GET("api/usuarios")
    suspend fun obtenerUsuarios(): List<UsuarioApi>

    @POST("api/usuarios/registro")
    suspend fun registrarUsuario(
        @Body usuario: UsuarioApi
    ): UsuarioApi?

    @POST("api/usuarios/login")
    suspend fun loginUsuario(
        @Body usuario: UsuarioApi
    ): UsuarioApi?
}