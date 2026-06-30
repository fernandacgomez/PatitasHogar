package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminScreen(
    onVerDonaciones: () -> Unit,
    onCerrarSesion: () -> Unit
) {

    val verde = Color(0xFF0DB14B)
    val verdeOscuro = Color(0xFF087A35)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color(0xFFF3FFF6)
                    )
                )
            )
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(65.dp),
                shape = CircleShape,
                color = verde
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        "🛡",
                        fontSize = 34.sp
                    )

                }

            }

            Spacer(modifier = Modifier.width(15.dp))

            Column {

                Text(
                    text = "Administrador",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = verdeOscuro
                )

                Text(
                    text = "Panel de Control",
                    color = Color.Gray
                )

            }

        }

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    "📊 Resumen",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(15.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(15.dp))

                Text("🐶 Gestión de mascotas")
                Spacer(modifier = Modifier.height(8.dp))
                Text("💰 Gestión de donaciones")
                Spacer(modifier = Modifier.height(8.dp))
                Text("👥 Administración de usuarios")

            }

        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onVerDonaciones,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            )
        ) {

            Text(
                "💰 Ver Donaciones",
                fontSize = 18.sp
            )

        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {

                // Aquí luego conectaremos la pantalla
                println("Agregar mascota")

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {

            Text(
                "🐶 Agregar Mascota",
                fontSize = 18.sp
            )

        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF43A047)
            )
        ) {

            Text(
                "📋 Administrar Mascotas (Próximamente)",
                fontSize = 17.sp
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onCerrarSesion,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {

            Text(
                "Cerrar sesión",
                fontSize = 18.sp
            )

        }

    }

}