package com.example.patitashogar.service

data class MascotaApi(
    val id: Long? = null,
    val nombre: String = "",
    val tipo: String = "",
    val raza: String = "",
    val edad: String = "",
    val sexo: String = "",
    val ciudad: String = "",
    val ubicacion: String = "",
    val descripcion: String = "",
    val estado: String = "",
    val imagenUrl: String = "",
    val fechaRegistro: String = ""
)