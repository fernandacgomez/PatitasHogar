package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Importaciones de tu amigo para que guarde la donación
import com.example.patitashogar.data.DatosPrueba
import com.example.patitashogar.model.Donacion

@Composable
fun DonacionScreen(onVolver: () -> Unit) {

    // Estados del formulario
    var otraCantidad by remember { mutableStateOf("") }
    var numeroTarjeta by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var nombreTarjeta by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // Colores del diseño
    val verdePrincipal = Color(0xFF0DB14B)
    val verdeClaroFondo = Color(0xFFF0FDF4)
    val colorFondoPantalla = Color(0xFFF8F9FA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondoPantalla)
            .verticalScroll(rememberScrollState())
    ) {

        // 🔝 HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(verdePrincipal)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Corazón verde
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("💚", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text("PatitasFelices", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Hola, Usuario", color = Color.White, fontSize = 14.sp)
            }

            // Ícono de usuario
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("👤", fontSize = 20.sp)
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // 💰 CAMPO MONTO
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = otraCantidad,
                        onValueChange = { otraCantidad = it },
                        placeholder = { Text("Otra cantidad", color = Color.Gray) },
                        leadingIcon = { Text("$", modifier = Modifier.padding(start = 16.dp)) },
                        trailingIcon = { Text("MXN", modifier = Modifier.padding(end = 16.dp), color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = verdePrincipal
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 💳 DATOS TARJETA
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Información de pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = numeroTarjeta,
                        onValueChange = { numeroTarjeta = it },
                        placeholder = { Text("Número de tarjeta") },
                        leadingIcon = { Text("💳", modifier = Modifier.padding(start = 16.dp), fontSize = 20.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            placeholder = { Text("MM/AA") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = cvv,
                            onValueChange = { cvv = it },
                            placeholder = { Text("CVV") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = nombreTarjeta,
                        onValueChange = { nombreTarjeta = it },
                        placeholder = { Text("Nombre en la tarjeta") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🧠 INFO
            Card(
                colors = CardDefaults.cardColors(containerColor = verdeClaroFondo),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("¿Cómo ayuda tu donación?", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(12.dp))

                    val beneficios = listOf(
                        "Alimento y cuidados básicos",
                        "Atención veterinaria",
                        "Esterilización",
                        "Refugio temporal",
                        "Programas de adopción"
                    )

                    beneficios.forEach { beneficio ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text("✓", color = verdePrincipal, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(beneficio, fontSize = 13.sp, color = Color(0xFF374151))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔘 BOTÓN DONAR
            Button(
                onClick = {
                    if(otraCantidad.isNotBlank() && nombreTarjeta.isNotBlank()) {
                        val nuevaDonacion = Donacion(
                            usuario = nombreTarjeta,
                            tipo = "Dinero",
                            cantidad = otraCantidad
                        )

                        DatosPrueba.listaDonaciones.add(nuevaDonacion)
                        println("Donación guardada: " + DatosPrueba.listaDonaciones)

                        mensaje = "¡Donación de $$otraCantidad registrada correctamente!"

                        // Limpiar formulario
                        otraCantidad = ""
                        numeroTarjeta = ""
                        fecha = ""
                        cvv = ""
                        nombreTarjeta = ""
                    } else {
                        mensaje = "Por favor, ingresa el monto y tu nombre."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = verdePrincipal),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Enviar donación", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            // Mensaje de éxito o error
            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = mensaje,
                    color = if(mensaje.contains("correctamente")) verdePrincipal else Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al inicio", color = Color.Gray)
            }
        }
    }
}