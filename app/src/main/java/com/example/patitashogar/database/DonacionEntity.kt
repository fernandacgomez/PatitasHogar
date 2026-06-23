package com.example.patitashogar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donaciones")
data class DonacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tipoDonacion: String, // "Efectivo" o "Insumo"

    val nombreDonante: String = "",

    // Para donación en efectivo
    val monto: String = "",

    // Para donación de insumos
    val tipoInsumo: String = "",
    val ciudad: String = "",
    val comentarios: String = "",

    val fechaRegistro: String = ""
)

