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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patitashogar.service.UsuarioApi

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onRegistrarUsuario: (UsuarioApi) -> Unit,
    onVolverLogin: () -> Unit
) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val verdePrincipal = Color(0xFF0DB14B)
    val verdeOscuro = Color(0xFF087A35)
    val verdeSuave = Color(0xFFEAF8EF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color(0xFFF7FCF8)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Surface(
            modifier = Modifier
                .size(120.dp)
                .shadow(8.dp, CircleShape),
            shape = CircleShape,
            color = verdeSuave
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "🐶",
                    fontSize = 54.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Crear Cuenta",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = verdeOscuro
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Únete a Patitas Hogar",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            leadingIcon = { Text("👤", fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            leadingIcon = { Text("✉️", fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Text("🔒", fontSize = 20.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (
                    nombre.isNotBlank() &&
                    correo.isNotBlank() &&
                    password.isNotBlank()
                ) {

                    onRegistrarUsuario(
                        UsuarioApi(
                            nombre = nombre,
                            correo = correo,
                            password = password,
                            rol = "USUARIO"
                        )
                    )

                    mensaje = "Cuenta creada correctamente."

                    onRegisterSuccess()

                } else {

                    mensaje = "Completa todos los campos."

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            )
        ) {

            Text(
                "Crear cuenta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(14.dp))

        if (mensaje.isNotEmpty()) {

            Text(
                text = mensaje,
                color = if (mensaje.contains("correctamente")) verdePrincipal else Color.Red
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        TextButton(
            onClick = onVolverLogin
        ) {

            Text(
                "← Ya tengo una cuenta",
                color = verdeOscuro,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(25.dp))

        Row {

            Text(
                "🐶",
                fontSize = 55.sp
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                "🐱",
                fontSize = 55.sp
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                "🐾",
                fontSize = 50.sp
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

    }

}