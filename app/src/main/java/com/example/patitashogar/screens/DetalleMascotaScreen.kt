package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.patitashogar.database.MascotaEntity

@Composable
fun DetalleMascotaScreen(
    mascota: MascotaEntity?,
    onVolver: () -> Unit
) {
    var mostrarContacto by remember { mutableStateOf(false) }
    val verdePrincipal = Color(0xFF0DB14B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F7))
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        Text(
            text = "Detalle de mascota",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (mascota != null) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Foto real si existe, emoji si no
                    if (mascota.fotoUri.isNotBlank()) {
                        AsyncImage(
                            model = mascota.fotoUri,
                            contentDescription = "Foto de ${mascota.nombre}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (mascota.especie.lowercase() == "perro") "🐶" else "🐱",
                                fontSize = 80.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = mascota.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Text("Especie: ${mascota.especie}")
                    Text("Raza: ${mascota.raza}")
                    Text("Edad: ${mascota.edad}")
                    Text("Estado: ${mascota.estado}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Descripción:",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = mascota.descripcion)

                    if (mascota.fechaRegistro.isNotBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Registrada: ${mascota.fechaRegistro}",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { mostrarContacto = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = verdePrincipal
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Contactar para adoptar")
                    }

                    if (mostrarContacto) {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFEAF8EF)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Contacto de adopción",
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Nombre: ${mascota.nombreContacto}")
                                Text("Teléfono: ${mascota.telefonoContacto}")
                            }
                        }
                    }
                }
            }
        } else {
            Text("No se seleccionó ninguna mascota.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVolver,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}