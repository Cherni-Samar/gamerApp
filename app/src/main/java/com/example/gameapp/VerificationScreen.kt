package com.example.gameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gameapp.ui.theme.GameAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    controller: NavHostController,
    onBackClick: () -> Unit = {controller.navigate("forgotPwd")},
    onVerifySuccess: () -> Unit = {controller.navigate("resetPwd")}
) {
    var code by remember { mutableStateOf("") }
    var wrongCode by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .padding(top = 80.dp), // remonter les carrés
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Titre
            Text(
                text = "Enter the 4-digit code sent to you",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Champ OTP avec carrés
            CodeInputWithSquares(
                code = code,
                onCodeChange = { newCode ->
                    val filtered = newCode.filter { it.isDigit() }
                    if (filtered.length <= 4) {
                        code = filtered
                        wrongCode = false

                        if (filtered.length == 4) {
                            val isValid = filtered == "1234" // Exemple
                            if (!isValid) {
                                wrongCode = true
                            } else {
                                onVerifySuccess()
                            }
                        }
                    }
                },
                isError = wrongCode,
                focusRequester = focusRequester
            )

            // Message d'erreur
            if (wrongCode) {
                Text(
                    text = "Wrong code!",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bouton Verify
            Button(
                onClick = {
                    if (code.length == 4) {
                        val isValid = code == "1234" // Exemple
                        if (!isValid) {
                            wrongCode = true
                        } else {
                            onVerifySuccess()
                        }
                    } else {
                        wrongCode = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                enabled = code.isNotEmpty()
            ) {
                Text("Verify", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Lien Resend code
            TextButton(
                onClick = {
                    code = ""
                    wrongCode = false
                }
            ) {
                Text(
                    text = "Didn't receive a verification code? Resend code",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    // Ouvrir le clavier automatiquement
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Composable
fun CodeInputWithSquares(
    code: String,
    onCodeChange: (String) -> Unit,
    isError: Boolean = false,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    // Détecter le clic sur les carrés pour focus + clavier
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentWidth()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            }
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(4) { index ->
                val char = code.getOrNull(index)?.toString() ?: ""
                val isFocused = index == code.length

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = 2.dp,
                            color = when {
                                isError -> MaterialTheme.colorScheme.error
                                isFocused -> MaterialTheme.colorScheme.primary
                                else -> Color.Gray
                            },
                            shape = MaterialTheme.shapes.medium
                        )
                        .background(
                            color = when {
                                isError -> MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                                isFocused -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                else -> Color.Transparent
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (char.isNotEmpty()) {
                        Text(
                            text = char,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isError) MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.primary
                        )
                    } else if (isFocused) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
        }

        // Champ texte caché invisible mais focusable
        BasicTextField(
            value = code,
            onValueChange = { newCode ->
                val filtered = newCode.filter { it.isDigit() }.take(4)
                onCodeChange(filtered)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .focusRequester(focusRequester)
                .size(1.dp)
        )
    }
}

