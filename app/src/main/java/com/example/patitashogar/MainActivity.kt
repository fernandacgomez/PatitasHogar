package com.example.patitashogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.patitashogar.model.Mascota
import com.example.patitashogar.screens.DetalleMascotaScreen
import com.example.patitashogar.screens.DonacionScreen
import com.example.patitashogar.screens.HomeScreen
import com.example.patitashogar.screens.LoginScreen
import com.example.patitashogar.screens.ReportarScreen
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

    when (pantallaActual) {
        "login" -> LoginScreen(
            onLoginClick = { pantallaActual = "home" }
        )

        "home" -> HomeScreen(
            onVerDetalle = { mascota ->
                mascotaSeleccionada = mascota
                pantallaActual = "detalle"
            },
            onIrReportar = { pantallaActual = "reportar" },
            onIrDonar = { pantallaActual = "donacion" },
            onCerrarSesion = { pantallaActual = "login" }
        )

        "detalle" -> DetalleMascotaScreen(
            mascota = mascotaSeleccionada,
            onVolver = { pantallaActual = "home" }
        )

        "reportar" -> ReportarScreen(
            onVolver = { pantallaActual = "home" }
        )

        "donacion" -> DonacionScreen(
            onVolver = { pantallaActual = "home" }
        )
    }
}