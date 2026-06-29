package com.example.patitashogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.patitashogar.model.Mascota
import com.example.patitashogar.repository.DonacionApiRepository
import com.example.patitashogar.screens.AdminDonacionesScreen
import com.example.patitashogar.screens.AdminScreen
import com.example.patitashogar.screens.DetalleMascotaScreen
import com.example.patitashogar.screens.DonacionScreen
import com.example.patitashogar.screens.DonacionTipoScreen
import com.example.patitashogar.screens.HomeScreen
import com.example.patitashogar.screens.InsumosScreen
import com.example.patitashogar.screens.LoginScreen
import com.example.patitashogar.screens.RegisterScreen
import com.example.patitashogar.screens.ReportarScreen
import com.example.patitashogar.service.DonacionApi
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
    var mascotaSeleccionada by remember { mutableStateOf<Mascota?>(null) }
    var usuarioActual by remember { mutableStateOf("") }

    var donacionesApi by remember {
        mutableStateOf<List<DonacionApi>>(emptyList())
    }

    val scope = rememberCoroutineScope()

    val donacionApiRepository = remember {
        DonacionApiRepository()
    }

    when (pantallaActual) {

        "login" -> {
            LoginScreen(
                onUserLogin = { nombre ->
                    usuarioActual = nombre
                    pantallaActual = "home"
                },
                onAdminLogin = {
                    pantallaActual = "admin"
                },
                onIrRegistro = {
                    pantallaActual = "registro"
                }
            )
        }

        "registro" -> {
            RegisterScreen(
                onRegisterSuccess = {
                    pantallaActual = "login"
                }
            )
        }

        "home" -> {
            HomeScreen(
                nombreUsuario = usuarioActual,
                onVerDetalle = { mascota ->
                    mascotaSeleccionada = mascota
                    pantallaActual = "detalle"
                },
                onIrReportar = {
                    pantallaActual = "reportar"
                },
                onIrDonar = {
                    pantallaActual = "tipoDonacion"
                },
                onCerrarSesion = {
                    pantallaActual = "login"
                }
            )
        }

        "detalle" -> {
            DetalleMascotaScreen(
                mascota = mascotaSeleccionada,
                onVolver = {
                    pantallaActual = "home"
                }
            )
        }

        "reportar" -> {
            ReportarScreen(
                onVolver = {
                    pantallaActual = "home"
                }
            )
        }

        "tipoDonacion" -> {
            DonacionTipoScreen(
                onEfectivoClick = {
                    pantallaActual = "donacion"
                },
                onInsumoClick = {
                    pantallaActual = "insumos"
                }
            )
        }

        "donacion" -> {
            DonacionScreen(
                onVolver = {
                    pantallaActual = "home"
                }
            )
        }

        "insumos" -> {
            InsumosScreen(
                onVolver = {
                    pantallaActual = "home"
                },
                onGuardarDonacion = { donacion ->
                    scope.launch {
                        try {
                            donacionApiRepository.guardarDonacion(donacion)
                            pantallaActual = "home"
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )
        }

        "admin" -> {
            AdminScreen(
                onVerDonaciones = {
                    scope.launch {
                        try {
                            donacionesApi = donacionApiRepository.obtenerDonaciones()
                            pantallaActual = "adminDonaciones"
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                },
                onCerrarSesion = {
                    pantallaActual = "login"
                }
            )
        }

        "adminDonaciones" -> {
            AdminDonacionesScreen(
                donaciones = donacionesApi,
                onVolver = {
                    pantallaActual = "admin"
                }
            )
        }
    }
}