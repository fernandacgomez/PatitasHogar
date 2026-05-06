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
import com.example.patitashogar.data.DatosPrueba
import com.example.patitashogar.model.Mascota

@Composable
fun ReportarScreen(
    onVolver: () -> Unit
) {

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
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
            text = "Reportar mascota",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = especie,
            onValueChange = { especie = it },
            label = { Text("Especie (Perro, Gato, etc)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza del animal") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = lugar,
            onValueChange = { lugar = it },
            label = { Text("Lugar donde fue encontrada") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción de la mascota") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                val nuevaMascota = Mascota(
                    id = DatosPrueba.mascotas.size + 1,
                    nombre = "Mascota encontrada",
                    especie = especie,
                    raza = raza,
                    edad = "Desconocida",
                    descripcion = "$descripcion (Encontrada en: $lugar)",
                    estado = "Reportada",
                    nombreContacto = nombre,
                    telefonoContacto = telefono
                )

                DatosPrueba.mascotas.add(nuevaMascota)

                mensaje = "Reporte enviado correctamente."

                nombre = ""
                telefono = ""
                especie = ""
                raza = ""
                lugar = ""
                descripcion = ""
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar reporte")
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