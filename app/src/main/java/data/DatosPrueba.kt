package com.example.patitashogar.data

import com.example.patitashogar.model.Mascota

object DatosPrueba {

    val mascotas = mutableListOf(
        Mascota(
            id = 1,
            nombre = "Luna",
            especie = "Perro",
            raza = "Mestiza",
            edad = "2 años",
            descripcion = "Es una perrita tranquila, cariñosa y muy sociable.",
            estado = "Disponible para adopción"
        ),
        Mascota(
            id = 2,
            nombre = "Milo",
            especie = "Gato",
            raza = "Criollo",
            edad = "1 año",
            descripcion = "Es un gatito curioso, juguetón y cariñoso.",
            estado = "Disponible para adopción"
        ),
        Mascota(
            id = 3,
            nombre = "Canela",
            especie = "Perro",
            raza = "Labrador mestiza",
            edad = "3 años",
            descripcion = "Es una perrita obediente, noble y protectora.",
            estado = "Disponible para adopción"
        )
    )
}