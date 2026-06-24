package com.example.patitashogar.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.patitashogar.database.MascotaEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

// Crea un archivo temporal para guardar la foto
fun crearArchivoFoto(context: Context): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val carpeta = File(context.filesDir, "fotos_mascotas")
    if (!carpeta.exists()) carpeta.mkdirs()
    return File(carpeta, "mascota_$timestamp.jpg")
}

@Composable
fun ReportarScreen(
    onVolver: () -> Unit,
    onGuardarMascota: (MascotaEntity) -> Unit
) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // URI de la foto capturada
    var fotoUri by remember { mutableStateOf<Uri?>(null) }
    // Archivo temporal donde se guarda la foto
    var archivoFoto by remember { mutableStateOf<File?>(null) }

    val verdePrincipal = Color(0xFF0DB14B)
    val verdeOscuro = Color(0xFF087A35)

    // Launcher para tomar foto con la cámara
    val launcherCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito ->
        if (exito && archivoFoto != null) {
            fotoUri = Uri.fromFile(archivoFoto)
        }
    }

    // Launcher para pedir permiso de cámara
    val launcherPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            val archivo = crearArchivoFoto(context)
            archivoFoto = archivo
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                archivo
            )
            launcherCamara.launch(uri)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F7))
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        Text(
            text = "Reportar mascota",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = especie,
            onValueChange = { especie = it },
            label = { Text("Especie (Perro, Gato, etc)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza del animal") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = lugar,
            onValueChange = { lugar = it },
            label = { Text("Lugar donde fue encontrada") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción de la mascota") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── SECCIÓN FOTO ──────────────────────────────────────────
        Text(
            text = "Foto de la mascota",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Previsualización de la foto
        if (fotoUri != null) {
            AsyncImage(
                model = fotoUri,
                contentDescription = "Foto de la mascota",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, verdePrincipal, RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFEAF8EF))
                    .border(2.dp, verdePrincipal, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "📷", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Sin foto aún",
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                launcherPermiso.launch(android.Manifest.permission.CAMERA)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = verdeOscuro
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (fotoUri != null) "📷 Tomar otra foto" else "📷 Tomar foto"
            )
        }
        // ─────────────────────────────────────────────────────────

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (especie.isBlank() || nombre.isBlank()) {
                    mensaje = "Por favor completa los campos requeridos."
                    return@Button
                }

                val fecha = SimpleDateFormat(
                    "dd/MM/yyyy HH:mm",
                    Locale.getDefault()
                ).format(Date())

                val nuevaMascota = MascotaEntity(
                    nombre = "Mascota encontrada",
                    especie = especie,
                    raza = raza,
                    edad = "Desconocida",
                    descripcion = "$descripcion (Encontrada en: $lugar)",
                    estado = "Reportada",
                    nombreContacto = nombre,
                    telefonoContacto = telefono,
                    fotoUri = fotoUri?.toString() ?: "",
                    fechaRegistro = fecha
                )

                onGuardarMascota(nuevaMascota)
                mensaje = "Reporte enviado correctamente."

                nombre = ""
                telefono = ""
                especie = ""
                raza = ""
                lugar = ""
                descripcion = ""
                fotoUri = null
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar reporte")
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mensaje,
                color = if (mensaje.contains("correctamente")) verdePrincipal else Color.Red,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVolver,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}