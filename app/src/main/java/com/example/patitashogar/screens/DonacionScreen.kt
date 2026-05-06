package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.patitashogar.data.DatosPrueba
import com.example.patitashogar.model.Donacion

@Composable
fun DonacionScreen(
    onVolver: () -> Unit
) {

    var usuario by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val verdePrincipal = Color(0xFF0DB14B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F7))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text("Realizar Donación")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Nombre del donante") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de donación (Dinero, Comida, Medicinas)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad o monto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val nuevaDonacion = Donacion(
                    usuario = usuario,
                    tipo = tipo,
                    cantidad = cantidad
                )

                DatosPrueba.listaDonaciones.add(nuevaDonacion)

                println(DatosPrueba.listaDonaciones)

                mensaje = "Donación registrada correctamente"

                usuario = ""
                tipo = ""
                cantidad = ""

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Enviar donación")
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (mensaje.isNotEmpty()) {

            Text(
                text = mensaje,
                color = verdePrincipal
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

    }

}