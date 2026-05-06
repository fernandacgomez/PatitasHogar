package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DonacionScreen(
    onVolver: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var tipoDonacion by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val verdePrincipal = Color(0xFF0DB14B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F7))
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        Text(
            text = "Donaciones",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Podés donar comida, vacunas, mantas, juguetes, dinero u otros insumos."
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del donante") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = tipoDonacion,
            onValueChange = { tipoDonacion = it },
            label = { Text("Tipo de donación") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                mensaje = "Donación registrada correctamente. Gracias por apoyar."
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar donación")
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mensaje,
                color = verdePrincipal,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVolver,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}