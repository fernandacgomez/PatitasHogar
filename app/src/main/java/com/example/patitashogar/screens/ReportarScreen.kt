package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patitashogar.service.MascotaApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReportarScreen(
    onVolver: () -> Unit,
    onGuardarMascota: (MascotaApi) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var mostrarAyuda by remember { mutableStateOf(false) }

    val verde = Color(0xFF0DB14B)
    val verdeOscuro = Color(0xFF154D2E)
    val verdeSuave = Color(0xFFEAF8EF)
    val fondo = Color(0xFFFAFBFA)

    if (mostrarAyuda) {
        AlertDialog(
            onDismissRequest = { mostrarAyuda = false },
            confirmButton = {
                TextButton(onClick = { mostrarAyuda = false }) {
                    Text("Entendido", color = verde)
                }
            },
            title = { Text("¿Cómo llenar el reporte?") },
            text = {
                Text(
                    "Completa tus datos de contacto, escribe la especie, raza si la sabes, el lugar donde viste la mascota y una descripción clara. Si no sabes algún dato, escribe “Desconocido”."
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(46.dp)
                    .clickable { onVolver() },
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 3.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("←", fontSize = 28.sp, color = verdeOscuro)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = verdeSuave
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("🐾", fontSize = 25.sp)
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Reportar mascota",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = verdeOscuro,
                    maxLines = 1
                )

                Text(
                    text = "Ayúdanos a reunirlos con su familia 💚",
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            Surface(
                modifier = Modifier
                    .size(44.dp)
                    .clickable { mostrarAyuda = true },
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 3.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("?", fontSize = 21.sp, color = verdeOscuro)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = verdeSuave)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🐶🐱", fontSize = 38.sp)

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Cada reporte cuenta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = verdeOscuro,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Tu reporte puede ayudar a que esta mascota regrese a casa.",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                }

                Text("🐾", fontSize = 22.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "🐾 Información de la mascota",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    color = verdeOscuro
                )

                Spacer(modifier = Modifier.height(18.dp))

                CampoReporte(nombre, { nombre = it }, "👤", "Nombre de la persona", "¿Quién encontró o está reportando?")
                CampoReporte(telefono, { telefono = it }, "📞", "Teléfono", "Por si necesitamos contactarte")
                CampoReporte(especie, { especie = it }, "🐾", "Especie (Perro, Gato, etc)", "Ejemplo: Perro, Gato, Conejo")
                CampoReporte(raza, { raza = it }, "🐶", "Raza del animal", "Si no lo sabes, escribe “Desconocida”")
                CampoReporte(lugar, { lugar = it }, "📍", "Lugar donde fue encontrada", "Dirección o referencia del lugar")

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { if (it.length <= 250) descripcion = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    placeholder = {
                        Text(
                            text = "Descripción de la mascota",
                            maxLines = 1
                        )
                    },
                    supportingText = {
                        Text("Color, tamaño, características, estado, etc.")
                    },
                    leadingIcon = {
                        Text("📋", fontSize = 22.sp)
                    },
                    trailingIcon = {
                        Text(
                            text = "${descripcion.length}/250",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(18.dp)
                )

                Spacer(modifier = Modifier.height(22.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier.size(44.dp),
                        shape = CircleShape,
                        color = verdeSuave
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("📷", fontSize = 22.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Foto de la mascota",
                            fontWeight = FontWeight.Bold,
                            color = verdeOscuro,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Una foto ayuda mucho a encontrarlo",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFBFDFB))
                        .border(
                            width = 1.dp,
                            color = Color(0xFF9DBB9F),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Surface(
                            modifier = Modifier.size(70.dp),
                            shape = CircleShape,
                            color = verdeSuave
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("📸", fontSize = 34.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Agrega una foto de la mascota",
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "Formato JPG o PNG. Tamaño máximo 5MB",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        mensaje = "La cámara se conectará después."
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("📷 Tomar foto", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (
                    nombre.isNotBlank() &&
                    telefono.isNotBlank() &&
                    especie.isNotBlank() &&
                    lugar.isNotBlank() &&
                    descripcion.isNotBlank()
                ) {
                    val nuevaMascota = MascotaApi(
                        nombre = "Mascota encontrada",
                        tipo = especie,
                        raza = raza,
                        edad = "Desconocida",
                        sexo = "No especificado",
                        ciudad = "Managua",
                        ubicacion = lugar,
                        descripcion = "Descripción: $descripcion. Reportante: $nombre. Teléfono: $telefono.",
                        estado = "Reportada",
                        imagenUrl = "",
                        fechaRegistro = obtenerFechaActualReporte()
                    )

                    onGuardarMascota(nuevaMascota)
                    mensaje = "Reporte enviado correctamente."

                    nombre = ""
                    telefono = ""
                    especie = ""
                    raza = ""
                    lugar = ""
                    descripcion = ""
                } else {
                    mensaje = "Completa los campos principales."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = verde),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("📨 Enviar reporte", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = mensaje,
                color = if (mensaje.contains("correctamente")) verde else Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "🔒 Tu información está protegida",
            color = Color(0xFF6B8E6D),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
fun CampoReporte(
    value: String,
    onValueChange: (String) -> Unit,
    icono: String,
    label: String,
    supporting: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        label = { Text(label) },
        placeholder = { Text(supporting) },
        leadingIcon = { Text(icono, fontSize = 22.sp) },
        shape = RoundedCornerShape(18.dp),
        singleLine = true
    )
}

fun obtenerFechaActualReporte(): String {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formato.format(Date())
}