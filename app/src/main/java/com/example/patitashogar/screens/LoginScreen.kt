package com.example.patitashogar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

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
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Surface(
            modifier = Modifier
                .size(130.dp)
                .shadow(8.dp, CircleShape),
            shape = CircleShape,
            color = verdeSuave
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🏠",
                    fontSize = 48.sp
                )

                Text(
                    text = "🐾",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Patitas Hogar",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = verdeOscuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sistema de adopción y cuidado veterinario",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(34.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Text(text = "✉️", fontSize = 20.sp)
            },
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            leadingIcon = {
                Text(text = "🔒", fontSize = 20.sp)
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = verdePrincipal
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        TextButton(onClick = { }) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = verdeOscuro,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "🐶", fontSize = 82.sp)
            Text(text = "🐱", fontSize = 78.sp)
        }
    }
}
