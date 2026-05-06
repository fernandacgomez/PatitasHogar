package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.patitashogar.model.Mascota

@Composable
fun DetalleMascotaScreen(
    mascota: Mascota?,
    onVolver: () -> Unit
) {
    var mostrarContacto by remember { mutableStateOf(false) }
    val verdePrincipal = Color(0xFF0DB14B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F7))
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
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (mascota.especie.lowercase() == "perro") "🐶" else "🐱"
                        )
                    }

                    Text(
                        text = mascota.nombre,
                        fontWeight = FontWeight.Bold
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
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
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