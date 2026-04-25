package com.example.primeraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.primeraapp.ui.theme.PrimeraAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeForm()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloComposeForm(){
    var name by remember { mutableStateOf("") }
    var documentId by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            birthday = sdf.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    
    Scaffold (
        topBar = {
            TopAppBar(title = {Text("Hola ESAN")})
        }
    ){ padding ->
        Column (
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Bienvenido a Jecpack Compose")
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {Text("Nombre")}
            )
            OutlinedTextField(
                value = documentId,
                onValueChange = { newValue ->
                    // Solo permite números
                    if (newValue.all { it.isDigit() } && newValue.length <= 12) {
                        documentId = newValue
                    }
                },
                label = {Text("Documento de Identidad")},
                placeholder = {Text("Ingrese solo números")},
                isError = documentId.isNotEmpty() && documentId.length < 8,
                supportingText = {
                    if (documentId.isNotEmpty() && documentId.length < 8) {
                        Text("El DNI debe tener al menos 8 dígitos", color = MaterialTheme.colorScheme.error)
                    }
                }
            )
            Button(
                onClick = { showDatePicker = true },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(if (birthday.isEmpty()) "Seleccionar Cumpleaños" else "Cumpleaños: $birthday")
            }
            Button(
                onClick = {},
                enabled = name.isNotEmpty() && documentId.isNotEmpty() && documentId.length >= 8 && birthday.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Enviar")
            }
        }
    }
}