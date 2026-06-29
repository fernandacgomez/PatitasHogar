package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patitashogar.service.DonacionApi

@Composable
fun AdminDonacionesScreen(
    donaciones: List<DonacionApi>,
    onVolver: () -> Unit
) {
    val verde = Color(0xFF2E7D32)
    val fondo = Color(0xFFFAFAF7)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Donaciones registradas",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = verde,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Historial guardado en PostgreSQL mediante API REST",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF3E6)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "❤",
                        fontSize = 28.sp,
                        color = verde
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Total de donaciones",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D32)
                    )

                    Text(
                        text = "${donaciones.size} registradas",
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (donaciones.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🐾",
                        fontSize = 45.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Aún no hay donaciones registradas.",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Cuando un usuario registre una donación, aparecerá aquí.",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            donaciones.forEach { donacion ->
                DonacionCard(donacion = donacion)
                Spacer(modifier = Modifier.height(14.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onVolver() },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            )
        ) {
            Text(
                text = "Volver al panel",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DonacionCard(
    donacion: DonacionApi
) {
    val verde = Color(0xFF2E7D32)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (donacion.tipoDonacion == "Insumo") "📦" else "💵",
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Donación de ${donacion.tipoDonacion}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = verde
                    )

                    Text(
                        text = donacion.fechaRegistro,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (donacion.tipoDonacion == "Insumo") {
                Text(
                    text = "Tipo de insumo: ${donacion.tipoInsumo}",
                    fontSize = 15.sp,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "Ciudad: ${donacion.ciudad}",
                    fontSize = 15.sp,
                    color = Color(0xFF333333)
                )

                if (donacion.comentarios.isNotBlank()) {
                    Text(
                        text = "Comentarios: ${donacion.comentarios}",
                        fontSize = 15.sp,
                        color = Color(0xFF333333)
                    )
                }
            } else {
                Text(
                    text = "Monto: ${donacion.monto}",
                    fontSize = 15.sp,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}