package com.example.patitashogar.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.patitashogar.data.DatosPrueba


@Composable
fun AdminDonacionesScreen(
    onVolver: () -> Unit
) {

    val lista = DatosPrueba.listaDonaciones


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Donaciones Recibidas")

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(lista) { donacion ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text("Nombre: ${donacion.usuario}")
                        Text("Tipo: ${donacion.tipo}")
                        Text("Monto: ${donacion.cantidad}")

                    }

                }

            }

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