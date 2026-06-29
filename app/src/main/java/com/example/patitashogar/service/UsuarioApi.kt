package com.example.patitashogar.service

data class UsuarioApi(
    val id: Long? = null,
    val nombre: String = "",
    val correo: String = "",
    val password: String = "",
    val rol: String = ""
)