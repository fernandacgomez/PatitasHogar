package com.example.patitashogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.patitashogar.database.MascotaEntity
import com.example.patitashogar.database.PatitasDatabase
import com.example.patitashogar.repository.DonacionRepository
import com.example.patitashogar.repository.MascotaRepository
import com.example.patitashogar.screens.*
import com.example.patitashogar.ui.theme.PatitasHogarTheme
import kotlinx.coroutines.launch

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

    var pantallaActual by remember { mutableStateOf("login") }
    var mascotaSeleccionada by remember { mutableStateOf<MascotaEntity?>(null) }
    var usuarioActual by remember { mutableStateOf("") }

    val context = LocalContext.current

    val database = remember { PatitasDatabase.obtenerDatabase(context) }

    val donacionRepository = remember { DonacionRepository(database.donacionDao()) }
    val mascotaRepository = remember { MascotaRepository(database.mascotaDao()) }

    val donaciones by donacionRepository.todasLasDonaciones.collectAsState(initial = emptyList())
    val mascotas by mascotaRepository.todasLasMascotas.collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()

    when (pantallaActual) {

        "login" -> LoginScreen(
            onUserLogin = { nombre ->
                usuarioActual = nombre
                pantallaActual = "home"
            },
            onAdminLogin = { pantallaActual = "admin" },
            onIrRegistro = { pantallaActual = "registro" }
        )

        "registro" -> RegisterScreen(
            onRegisterSuccess = { pantallaActual = "login" }
        )

        "home" -> HomeScreen(
            nombreUsuario = usuarioActual,
            mascotas = mascotas,
            onVerDetalle = { mascota ->
                mascotaSeleccionada = mascota
                pantallaActual = "detalle"
            },
            onIrReportar = { pantallaActual = "reportar" },
            onIrDonar = { pantallaActual = "tipoDonacion" },
            onCerrarSesion = { pantallaActual = "login" }
        )

        "detalle" -> DetalleMascotaScreen(
            mascota = mascotaSeleccionada,
            onVolver = { pantallaActual = "home" }
        )

        "reportar" -> ReportarScreen(
            onVolver = { pantallaActual = "home" },
            onGuardarMascota = { mascota ->
                scope.launch {
                    mascotaRepository.insertarMascota(mascota)
                    pantallaActual = "home"
                }
            }
        )

        "tipoDonacion" -> DonacionTipoScreen(
            onEfectivoClick = { pantallaActual = "donacion" },
            onInsumoClick = { pantallaActual = "insumos" }
        )

        "donacion" -> DonacionScreen(
            onVolver = { pantallaActual = "home" }
        )

        "insumos" -> InsumosScreen(
            onVolver = { pantallaActual = "home" },
            onGuardarDonacion = { donacion ->
                scope.launch {
                    donacionRepository.insertarDonacion(donacion)
                    pantallaActual = "home"
                }
            }
        )

        "admin" -> AdminScreen(
            onVerDonaciones = { pantallaActual = "adminDonaciones" },
            onCerrarSesion = { pantallaActual = "login" }
        )

        "adminDonaciones" -> AdminDonacionesScreen(
            donaciones = donaciones,
            onVolver = { pantallaActual = "admin" }
        )
    }
}