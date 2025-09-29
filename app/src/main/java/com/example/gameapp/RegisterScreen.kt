package com.example.gameapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun RegisterScreen(
    onLoginClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

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

        // Champ Full Name
        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                fullNameError = it.isEmpty()
            },
            label = { Text("FullName") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Full Name")
            },
            isError = fullNameError,
            supportingText = {
                if (fullNameError) {
                    Text(
                        text = "Must not be empty!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isEmpty()
            },
            label = { Text("Email") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(
                        text = "Must not be empty!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ Password
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
                        text = "Must not be empty!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ Confirm Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = it.isEmpty() || it != password
            },
            label = { Text("Confirm Password") },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Confirm Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = confirmPasswordError,
            supportingText = {
                if (confirmPasswordError) {
                    Text(
                        text = if (confirmPassword.isEmpty()) "Must not be empty!" else "Passwords don't match!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Bouton Submit
        Button(
            onClick = {
                fullNameError = fullName.isEmpty()
                emailError = email.isEmpty()
                passwordError = password.isEmpty()
                confirmPasswordError = confirmPassword.isEmpty() || confirmPassword != password

                if (!fullNameError && !emailError && !passwordError && !confirmPasswordError) {
                    // TODO: Registration logic
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Submit", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Conditions et politique de confidentialité
        Text(
            text = "By registering you agree to our Terms & Conditions and Privacy Policy",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Lien pour se connecter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ")
            TextButton(onClick = onLoginClick) {
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}