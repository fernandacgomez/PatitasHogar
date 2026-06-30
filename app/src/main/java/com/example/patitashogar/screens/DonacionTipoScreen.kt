package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
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
    onVolver: () -> Unit,
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
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(52.dp)
                    .clickable { onVolver() },
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "←",
                        fontSize = 30.sp,
                        color = verde,
                        modifier = Modifier.offset(y = (-3).dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "¿Qué te gustaría donar?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B1B1B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = "Elige la opción que mejor se adapte\na tu forma de ayudar.",
            fontSize = 16.sp,
            color = Color(0xFF555555),
            textAlign = TextAlign.Center,
            lineHeight = 23.sp
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
                .height(64.dp),
            shape = RoundedCornerShape(16.dp),
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
                    fontSize = 28.sp,
                    color = verde
                )

                Spacer(modifier = Modifier.width(18.dp))

                Text(
                    text = "Cada donación ayuda a mejorar la vida de nuestros peludos. ¡Gracias!",
                    fontSize = 14.sp,
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
        shape = RoundedCornerShape(16.dp),
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