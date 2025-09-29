package com.example.gameapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Titre GAMER
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Gamer",
            modifier = Modifier
                .size(120.dp) // taille de ton logo
                .padding(bottom = 48.dp)
        )

        // Champ Email avec icône
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isEmpty()
            },
            label = { Text("Mail") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(
                        text = "Mail not be empty!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Champ Password avec icône
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = it.isEmpty()
            },
            label = { Text("Password") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError,
            supportingText = {
                if (passwordError) {
                    Text(
                        text = "Mail not be empty!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ligne Remember Me et Forget Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Remember Me avec Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = "Remember Me",
                    fontSize = 14.sp
                )
            }

            // Forget Password
            TextButton(onClick = { /* TODO: Navigate to forget password */ }) {
                Text(
                    text = "Forget password ?",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Bouton Login
        Button(
            onClick = {
                emailError = email.isEmpty()
                passwordError = password.isEmpty()
                // TODO: Authentication logic
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Login", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Séparateur OR
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.Gray
            )
            Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Boutons Feedback et Google
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = { /* TODO: Feedback */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Feedback")
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(
                onClick = { /* TODO: Google login */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Google")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Ligne Register Now
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ")
            TextButton(onClick = { /* TODO: Navigate to register */ }) {
                Text(
                    text = "Register Now",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}