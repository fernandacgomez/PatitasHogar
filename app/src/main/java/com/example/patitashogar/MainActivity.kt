package com.example.patitashogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.patitashogar.data.DatosPrueba
import com.example.patitashogar.model.Mascota
import com.example.patitashogar.ui.theme.PatitasHogarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PatitasHogarTheme {
                AppPatitasHogar()
            }
        }
    }
}

@Composable
fun AppPatitasHogar() {
    var pantallaActual by remember { mutableStateOf("inicio") }
    var mascotaSeleccionada by remember { mutableStateOf<Mascota?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        when (pantallaActual) {
            "inicio" -> PantallaInicio(
                padding = innerPadding,
                onVerMascotas = { pantallaActual = "mascotas" },
                onReportarMascota = { pantallaActual = "reportar" },
                onDonar = { pantallaActual = "donaciones" }
            )

            "mascotas" -> PantallaMascotas(
                padding = innerPadding,
                onVolver = { pantallaActual = "inicio" },
                onVerDetalle = { mascota ->
                    mascotaSeleccionada = mascota
                    pantallaActual = "detalle"
                }
            )

            "detalle" -> PantallaDetalleMascota(
                padding = innerPadding,
                mascota = mascotaSeleccionada,
                onVolver = { pantallaActual = "mascotas" }
            )

            "reportar" -> PantallaReportarMascota(
                padding = innerPadding,
                onVolver = { pantallaActual = "inicio" }
            )

            "donaciones" -> PantallaDonaciones(
                padding = innerPadding,
                onVolver = { pantallaActual = "inicio" }
            )
        }
    }
}

@Composable
fun FondoPrincipal(
    padding: PaddingValues,
    contenido: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3E0),
                        Color(0xFFFFE0B2),
                        Color(0xFFFFCC80)
                    )
                )
            )
            .padding(20.dp)
    ) {
        contenido()
    }
}

@Composable
fun PantallaInicio(
    padding: PaddingValues,
    onVerMascotas: () -> Unit,
    onReportarMascota: () -> Unit,
    onDonar: () -> Unit
) {
    FondoPrincipal(padding) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Patitas Hogar",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Ayudanos a mascotas encontradas a tener una segunda oportunidad.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF6D4C41),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.9f)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onVerMascotas,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver mascotas en adopción")
                }

                Button(
                    onClick = onReportarMascota,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reportar mascota encontrada")
                }

                Button(
                    onClick = onDonar,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Realizar donación")
                }
            }
        }
    }
}

@Composable
fun PantallaMascotas(
    padding: PaddingValues,
    onVolver: () -> Unit,
    onVerDetalle: (Mascota) -> Unit
) {
    FondoPrincipal(padding) {
        Text(
            text = "Mascotas en adopción",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(DatosPrueba.mascotas) { mascota ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onVerDetalle(mascota) },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.95f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = mascota.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4E342E)
                        )

                        Text("Especie: ${mascota.especie}")
                        Text("Raza: ${mascota.raza}")
                        Text("Edad: ${mascota.edad}")
                        Text("Estado: ${mascota.estado}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Tocar para ver detalle",
                            color = Color(0xFF8D6E63),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}

@Composable
fun PantallaDetalleMascota(
    padding: PaddingValues,
    mascota: Mascota?,
    onVolver: () -> Unit
) {
    FondoPrincipal(padding) {
        Text(
            text = "Detalle de mascota",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (mascota != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = mascota.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4E342E)
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
                        onClick = { },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Contactar para adoptar")
                    }
                }
            }
        } else {
            Text("No se seleccionó ninguna mascota.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a mascotas")
        }
    }
}

@Composable
fun PantallaReportarMascota(
    padding: PaddingValues,
    onVolver: () -> Unit
) {
    var nombrePersona by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    FondoPrincipal(padding) {
        Text(
            text = "Reportar mascota encontrada",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombrePersona,
            onValueChange = { nombrePersona = it },
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono de contacto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = especie,
            onValueChange = { especie = it },
            label = { Text("Especie de la mascota") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lugar,
            onValueChange = { lugar = it },
            label = { Text("Lugar donde fue encontrada") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                val nuevaMascota = Mascota(
                    id = DatosPrueba.mascotas.size + 1,
                    nombre = "Mascota encontrada",
                    especie = especie,
                    raza = "Desconocida",
                    edad = "No especificado",
                    descripcion = descripcion,
                    estado = "Reportada"
                )

                DatosPrueba.mascotas.add(nuevaMascota)

                mensaje = "Reporte enviado correctamente. La mascota fue agregada a la lista."
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar reporte")
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mensaje,
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}

@Composable
fun PantallaDonaciones(
    padding: PaddingValues,
    onVolver: () -> Unit
) {
    var nombreDonante by remember { mutableStateOf("") }
    var tipoDonacion by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    FondoPrincipal(padding) {
        Text(
            text = "Donaciones",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Podés apoyar con comida, mantas, vacunas, juguetes, productos de limpieza o donación monetaria.",
            color = Color(0xFF6D4C41)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombreDonante,
            onValueChange = { nombreDonante = it },
            label = { Text("Nombre del donante") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tipoDonacion,
            onValueChange = { tipoDonacion = it },
            label = { Text("Tipo de donación") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción de la donación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                mensaje = "Donación registrada. Gracias por apoyar a Patitas Hogar."
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar donación")
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mensaje,
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}