package com.example.patitashogar.service

data class DonacionApi(
    val id: Long? = null,
    val tipoDonacion: String,
    val nombreDonante: String = "",
    val monto: String = "",
    val tipoInsumo: String = "",
    val ciudad: String = "",
    val comentarios: String = "",
    val fechaRegistro: String = ""
)