package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun DonacionTipoScreen(
    onEfectivoClick: () -> Unit,
    onInsumoClick: () -> Unit
) {
    val fondo = Color(0xFFFAFAFA)
    val verde = Color(0xFF2E7D32)
    val verdeClaro = Color(0xFFEAF3E6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(42.dp))

        Text(
            text = "¿Qué te gustaría donar?",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B1B1B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = "Elige la opción que mejor se adapte\na tu forma de ayudar.",
            fontSize = 15.sp,
            color = Color(0xFF555555),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OpcionDonacionCard(
            icono = "$",
            titulo = "Donar dinero",
            descripcion = "Tu aporte económico nos ayuda\na cubrir gastos veterinarios,\nmedicina, alimentos y más.",
            verde = verde,
            verdeClaro = verdeClaro,
            onClick = onEfectivoClick
        )

        Spacer(modifier = Modifier.height(22.dp))

        OpcionDonacionCard(
            icono = "▧",
            titulo = "Donar insumos",
            descripcion = "Dona alimentos, medicamentos,\ncobijas, juguetes y otros artículos\nque nuestros peludos necesitan.",
            verde = verde,
            verdeClaro = verdeClaro,
            onClick = onInsumoClick
        )

        Spacer(modifier = Modifier.height(26.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "♥",
                    fontSize = 26.sp,
                    color = verde
                )

                Spacer(modifier = Modifier.width(18.dp))

                Text(
                    text = "Cada donación, grande o pequeña, hace\nuna gran diferencia en sus vidas. ¡Gracias!",
                    fontSize = 13.5.sp,
                    color = Color(0xFF555555),
                    lineHeight = 19.sp
                )
            }
        }
    }
}

@Composable
fun OpcionDonacionCard(
    icono: String,
    titulo: String,
    descripcion: String,
    verde: Color,
    verdeClaro: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 22.dp, end = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(verdeClaro),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icono,
                    fontSize = if (icono == "$") 46.sp else 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = verde
                )
            }

            Spacer(modifier = Modifier.width(19.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color = Color(0xFF4F4F4F),
                    lineHeight = 19.sp
                )
            }

            Text(
                text = "›",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )
        }
    }
}

