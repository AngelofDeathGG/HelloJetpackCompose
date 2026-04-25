package com.example.primeraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
    var birthday by remember { mutableStateOf("") }
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
                value = birthday,
                onValueChange = {birthday = it},
                label = {Text("Cumpleaños")}
            )
            Button(
                onClick = {},
                enabled = name.isNotEmpty() && birthday.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Enviar")
            }
        }
    }
}