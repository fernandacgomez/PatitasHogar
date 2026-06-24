package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun HomeScreen(
    nombreUsuario: String,
    mascotas: List<MascotaEntity>,               // ← ahora viene de Room
    onVerDetalle: (MascotaEntity) -> Unit,
    onIrReportar: () -> Unit,
    onIrDonar: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val verdePrincipal = Color(0xFF0DB14B)

    Scaffold(
        containerColor = Color(0xFFF5F6F7),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(verdePrincipal)
                    .padding(horizontal = 18.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White,
                        modifier = Modifier.size(54.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "💚", fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "PatitasHogar",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Hola, $nombreUsuario",
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }

                IconButton(onClick = onCerrarSesion) {
                    Text(text = "👤")
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
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Adopta un Amigo",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Encuentra a tu nuevo compañero. Todos bajo el cuidado de la casa hogar.",
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (mascotas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🐾", fontSize = 50.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Aún no hay mascotas reportadas.",
                            color = Color.Gray
                        )
                    }
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    items(mascotas) { mascota ->
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
    mascota: MascotaEntity,
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

                // Foto real o emoji
                if (mascota.fotoUri.isNotBlank()) {
                    AsyncImage(
                        model = mascota.fotoUri,
                        contentDescription = "Foto de ${mascota.nombre}",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Surface(
                        modifier = Modifier.size(90.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFEAF8EF)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (mascota.especie.lowercase() == "perro") "🐶" else "🐱",
                                fontSize = 40.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = mascota.nombre,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${mascota.especie} • ${mascota.raza}")
                    Text("${mascota.edad} • ${mascota.estado}")
                }

                Text(text = "♡")
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onVerDetalle,
                colors = ButtonDefaults.buttonColors(containerColor = verdePrincipal),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver detalles")
            }
        }
    }
}