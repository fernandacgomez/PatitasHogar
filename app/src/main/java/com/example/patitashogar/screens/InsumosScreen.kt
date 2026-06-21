package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class OpcionInsumo(
    val nombre: String,
    val icono: String,
    val descripcion: String
)

@Composable
fun InsumosScreen(
    onVolver: () -> Unit
) {
    val verde = Color(0xFF2E7D32)
    val verdeClaro = Color(0xFFEAF3E6)
    val fondo = Color(0xFFFAFAF7)

    val opciones = listOf(
        OpcionInsumo(
            nombre = "Alimentos",
            icono = "🥫",
            descripcion = "Croquetas, alimento húmedo, leche para cachorros/gatitos y snacks."
        ),
        OpcionInsumo(
            nombre = "Medicamentos",
            icono = "💊",
            descripcion = "Medicinas, vitaminas, productos de curación y suplementos."
        ),
        OpcionInsumo(
            nombre = "Cobijas y camas",
            icono = "🛏️",
            descripcion = "Cobijas, camitas, toallas y artículos para descanso."
        ),
        OpcionInsumo(
            nombre = "Juguetes",
            icono = "🧸",
            descripcion = "Pelotas, mordedores, juguetes y artículos de entretenimiento."
        ),
        OpcionInsumo(
            nombre = "Otros insumos",
            icono = "📦",
            descripcion = "Otros artículos útiles para el cuidado de los animales."
        )
    )

    var opcionSeleccionada by remember { mutableStateOf(opciones[0]) }
    var ciudad by remember { mutableStateOf("") }
    var tipoInsumo by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "‹",
                fontSize = 42.sp,
                color = Color(0xFF1B4D32),
                modifier = Modifier.clickable { onVolver() }
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐾",
                    fontSize = 28.sp
                )
            }
        }

        Text(
            text = "Donar insumos",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B4D32),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tu ayuda en forma de insumos\ntransforma vidas 💚",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp,
            color = Color(0xFF4F4F4F),
            textAlign = TextAlign.Center,
            lineHeight = 25.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(verdeClaro),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🐾",
                        fontSize = 30.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Al donar insumos, nos ayudas a brindarles lo que necesitan cada día para estar sanos, seguros y felices.",
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    lineHeight = 23.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "¿Qué puedes donar?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B4D32)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            opciones.forEach { opcion ->
                OpcionInsumoCard(
                    opcion = opcion,
                    seleccionado = opcionSeleccionada.nombre == opcion.nombre,
                    onClick = {
                        opcionSeleccionada = opcion
                        tipoInsumo = opcion.nombre
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F6EE))
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = opcionSeleccionada.icono,
                    fontSize = 54.sp
                )

                Spacer(modifier = Modifier.width(18.dp))

                Column {
                    Text(
                        text = opcionSeleccionada.nombre,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D32)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = opcionSeleccionada.descripcion,
                        fontSize = 15.sp,
                        color = Color(0xFF333333),
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "ⓘ En buen estado y dentro de su fecha de caducidad.",
                        fontSize = 13.sp,
                        color = Color(0xFF555555)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Cuéntanos sobre tu donación",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B4D32)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = ciudad,
                    onValueChange = { ciudad = it },
                    label = { Text("¿Desde dónde realizarás la donación?") },
                    placeholder = { Text("Ej: Managua") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = tipoInsumo,
                    onValueChange = { tipoInsumo = it },
                    label = { Text("¿Qué tipo de insumos donarás?") },
                    placeholder = { Text("Ej: alimentos, medicinas, juguetes") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = comentarios,
                    onValueChange = { comentarios = it },
                    label = { Text("Comentarios opcional") },
                    placeholder = { Text("Añade detalles sobre tu donación") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F6EE))
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "♡",
                    fontSize = 34.sp,
                    color = verde
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "¡Gracias por pensar en ellos!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D32)
                    )

                    Text(
                        text = "Cada insumo que donas mejora su calidad de vida.",
                        fontSize = 14.sp,
                        color = Color(0xFF555555)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = { onVolver() },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verde
            )
        ) {
            Text(
                text = "🐾  Continuar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "🔒 Tus datos están protegidos",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = Color(0xFF6B6B6B)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun OpcionInsumoCard(
    opcion: OpcionInsumo,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    val verde = Color(0xFF2E7D32)
    val bordeFondo = if (seleccionado) Color(0xFFEAF3E6) else Color.White

    Card(
        modifier = Modifier
            .width(118.dp)
            .height(130.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bordeFondo),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = opcion.icono,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = opcion.nombre,
                fontSize = 13.sp,
                color = if (seleccionado) verde else Color(0xFF333333),
                fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}