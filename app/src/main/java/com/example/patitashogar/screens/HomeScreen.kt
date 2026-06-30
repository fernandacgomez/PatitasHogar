package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patitashogar.data.DatosPrueba
import com.example.patitashogar.model.Mascota
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

@Composable
fun HomeScreen(
    nombreUsuario: String,
    onVerDetalle: (Mascota) -> Unit,
    onIrReportar: () -> Unit,
    onIrDonar: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val verdePrincipal = Color(0xFF0DB14B)
    var busqueda by remember { mutableStateOf("") }

    val mascotasFiltradas = DatosPrueba.mascotas.filter { mascota ->
        mascota.nombre.contains(busqueda, ignoreCase = true) ||
                mascota.especie.contains(busqueda, ignoreCase = true) ||
                mascota.raza.contains(busqueda, ignoreCase = true) ||
                mascota.estado.contains(busqueda, ignoreCase = true)
    }

    Scaffold(
        containerColor = Color(0xFFF5F6F7),
        topBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = verdePrincipal),
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(62.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("🐾", fontSize = 28.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Patitas Hogar",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )

                        Text(
                            text = "Bienvenido, $nombreUsuario 👋",
                            color = Color.White,
                            fontSize = 17.sp
                        )

                        Text(
                            text = "Encuentra un nuevo compañero",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 14.sp
                        )
                    }
                    var mostrarMenu by remember { mutableStateOf(false) }

                    Box {

                        Surface(
                            modifier = Modifier.size(52.dp),
                            color = Color.White.copy(alpha = 0.20f),
                            shape = RoundedCornerShape(18.dp)
                        ) {

                            IconButton(
                                onClick = { mostrarMenu = true }
                            ) {
                                Text(
                                    text = "👤",
                                    fontSize = 24.sp
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = mostrarMenu,
                            onDismissRequest = {
                                mostrarMenu = false
                            }
                        ) {

                            DropdownMenuItem(
                                text = {
                                    Text("🚪 Cerrar sesión")
                                },
                                onClick = {
                                    mostrarMenu = false
                                    onCerrarSesion()
                                }
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Text("💚") },
                    label = { Text("Adopción") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onIrReportar,
                    icon = { Text("🐾") },
                    label = { Text("Reportar") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onIrDonar,
                    icon = { Text("$") },
                    label = { Text("Donar") }
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 18.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Adopta un Amigo",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Encuentra a tu nuevo compañero bajo el cuidado de la casa hogar.",
                color = Color.DarkGray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por nombre, especie o raza...") },
                leadingIcon = { Text("🔍") },
                shape = RoundedCornerShape(18.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            if (mascotasFiltradas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron mascotas 🐾",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(mascotasFiltradas) { mascota ->
                        MascotaCard(
                            mascota = mascota,
                            onVerDetalle = { onVerDetalle(mascota) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MascotaCard(
    mascota: Mascota,
    onVerDetalle: () -> Unit
) {
    val verdePrincipal = Color(0xFF0DB14B)

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(92.dp),
                    shape = RoundedCornerShape(22.dp),
                    color = Color(0xFFEAF8EF)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = if (mascota.especie.lowercase() == "perro") "🐶" else "🐱",
                            fontSize = 34.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = mascota.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("${mascota.especie} • ${mascota.raza}", fontSize = 16.sp)
                    Text("${mascota.edad} • ${mascota.estado}", fontSize = 16.sp)
                }

                Text(text = "♡", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onVerDetalle,
                colors = ButtonDefaults.buttonColors(containerColor = verdePrincipal),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    "Ver detalles",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}