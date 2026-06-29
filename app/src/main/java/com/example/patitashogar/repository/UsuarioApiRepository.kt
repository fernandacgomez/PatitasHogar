package com.example.patitashogar.repository

import com.example.patitashogar.service.RetrofitClient
import com.example.patitashogar.service.UsuarioApi

class UsuarioApiRepository {

    private val api = RetrofitClient.usuarioApiService

    suspend fun obtenerUsuarios(): List<UsuarioApi> {
        return api.obtenerUsuarios()
    }

    suspend fun registrarUsuario(usuario: UsuarioApi): UsuarioApi? {
        return api.registrarUsuario(usuario)
    }

    suspend fun loginUsuario(usuario: UsuarioApi): UsuarioApi? {
        return api.loginUsuario(usuario)
    }
}