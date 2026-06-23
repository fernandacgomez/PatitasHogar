package com.example.patitashogar.data

import com.example.patitashogar.model.Mascota
import com.example.patitashogar.model.Donacion
import com.example.patitashogar.model.Usuario
object DatosPrueba {

    // LISTA DE MASCOTAS
    val mascotas = mutableListOf(

        Mascota(
            id = 1,
            nombre = "Max",
            especie = "Perro",
            raza = "Labrador",
            edad = "2 años",
            descripcion = "Perro juguetón",
            estado = "Disponible",
            nombreContacto = "Refugio",
            telefonoContacto = "0000"
        ),

        Mascota(
            id = 2,
            nombre = "Luna",
            especie = "Gato",
            raza = "Criollo",
            edad = "1 año",
            descripcion = "Muy cariñosa",
            estado = "Disponible",
            nombreContacto = "Refugio",
            telefonoContacto = "0000"
        )

    )


    // LISTA DE DONACIONES
    val listaDonaciones = mutableListOf<Donacion>()

    val usuarios = mutableListOf<Usuario>()

}