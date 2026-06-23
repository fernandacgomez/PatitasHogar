package com.example.patitashogar.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminScreen(
    onVerDonaciones: () -> Unit,
    onCerrarSesion: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Panel de Administrador")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onVerDonaciones,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Donaciones")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onCerrarSesion,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }

    }
}