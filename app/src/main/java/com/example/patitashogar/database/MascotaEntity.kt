package com.example.patitashogar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascotas")
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String = "",
    val especie: String = "",
    val raza: String = "",
    val edad: String = "",
    val descripcion: String = "",
    val estado: String = "Disponible",
    val nombreContacto: String = "",
    val telefonoContacto: String = "",
    val fotoUri: String = "",           // URI de la foto tomada
    val fechaRegistro: String = ""
)