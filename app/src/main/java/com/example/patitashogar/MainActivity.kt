package com.example.patitashogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.patitashogar.model.Mascota
import com.example.patitashogar.repository.DonacionApiRepository
import com.example.patitashogar.screens.*
import com.example.patitashogar.service.DonacionApi
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

    var pantallaActual by remember { mutableStateOf("login") }
    var mascotaSeleccionada by remember { mutableStateOf<Mascota?>(null) }
    var usuarioActual by remember { mutableStateOf("") }

    var donacionesApi by remember {
        mutableStateOf<List<DonacionApi>>(emptyList())
    }

    // Se deja por si luego vuelven a conectar la API
    val donacionApiRepository = remember {
        DonacionApiRepository()
    }

    when (pantallaActual) {

        // ---------------- LOGIN ----------------

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

        // ---------------- REGISTRO ----------------

        "registro" -> {
            RegisterScreen(
                onRegisterSuccess = {
                    pantallaActual = "login"
                },
                onRegistrarUsuario = {

                    println("Usuario registrado temporalmente")

                    pantallaActual = "login"

                },
                onVolverLogin = {
                    pantallaActual = "login"
                }
            )
        }

        // ---------------- HOME ----------------

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

        // ---------------- DETALLE ----------------

        "detalle" -> {
            DetalleMascotaScreen(
                mascota = mascotaSeleccionada,
                onVolver = {
                    pantallaActual = "home"
                }
            )
        }

        // ---------------- DONACIONES ----------------

        "tipoDonacion" -> {
            DonacionTipoScreen(
                onVolver = {
                    pantallaActual = "home"
                },
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
                },
                onGuardarDonacion = {

                    println("Donación guardada temporalmente")

                    pantallaActual = "home"

                }
            )
        }

        "insumos" -> {
            InsumosScreen(
                onVolver = {
                    pantallaActual = "home"
                },
                onGuardarDonacion = {

                    println("Donación de insumos guardada temporalmente")

                    pantallaActual = "home"

                }
            )
        }

        // ---------------- REPORTAR ----------------

        "reportar" -> {
            ReportarScreen(
                onVolver = {
                    pantallaActual = "home"
                },
                onGuardarMascota = {

                    println("Mascota reportada temporalmente")

                    pantallaActual = "home"

                }
            )
        }



        // ---------------- ADMIN ----------------

        "admin" -> {
            AdminScreen(
                onVerDonaciones = {

                    println("Mostrando donaciones temporales")

                    pantallaActual = "adminDonaciones"

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