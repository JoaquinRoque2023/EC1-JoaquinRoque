package pe.edu.idat.ec1_joaquinroque

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.idat.ec1_joaquinroque.ui.theme.EC1joaquinRoqueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EC1joaquinRoqueTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
        SalarioSemanalSection()
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        PromedioPracticasSection()
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        ConvertirTiempoSection()
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        SumaNumerosSection()
    }
}

@Composable
fun SalarioSemanalSection() {
    var horasTrabajadas by remember { mutableStateOf("") }
    var salario by remember { mutableStateOf(0.0) }

    Column {
        Text("Calcular Salario Semanal")
        TextField(
            value = horasTrabajadas,
            onValueChange = { horasTrabajadas = it },
            label = { Text("Horas Trabajadas") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val horas = horasTrabajadas.toDoubleOrNull() ?: 0.0
            salario = if (horas <= 40) {
                horas * 16
            } else {
                40 * 16 + (horas - 40) * 20
            }
        }) {
            Text("Calcular Salario")
        }
        Text("Salario: $salario")
    }
}

@Composable
fun PromedioPracticasSection() {
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var nota4 by remember { mutableStateOf("") }
    var promedio by remember { mutableStateOf(0.0) }
    var notaEliminada by remember { mutableStateOf(0.0) }

    Column {
        Text("Calcular Promedio de Prácticas")
        TextField(
            value = nota1,
            onValueChange = { nota1 = it },
            label = { Text("Nota 1") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = nota2,
            onValueChange = { nota2 = it },
            label = { Text("Nota 2") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = nota3,
            onValueChange = { nota3 = it },
            label = { Text("Nota 3") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = nota4,
            onValueChange = { nota4 = it },
            label = { Text("Nota 4") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val notas = listOf(
                nota1.toDoubleOrNull() ?: 0.0,
                nota2.toDoubleOrNull() ?: 0.0,
                nota3.toDoubleOrNull() ?: 0.0,
                nota4.toDoubleOrNull() ?: 0.0
            )
            val sortedNotas = notas.sorted()
            notaEliminada = sortedNotas.first()
            promedio = sortedNotas.drop(1).average()
        }) {
            Text("Calcular Promedio")
        }
        Text("Nota Eliminada: $notaEliminada")
        Text("Promedio: $promedio")
    }
}

@Composable
fun ConvertirTiempoSection() {
    var segundos by remember { mutableStateOf("") }
    var minutos by remember { mutableStateOf(0) }

    Column {
        Text("Convertir Segundos a Minutos")
        TextField(
            value = segundos,
            onValueChange = { segundos = it },
            label = { Text("Segundos") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val seg = segundos.toIntOrNull() ?: 0
            minutos = seg / 60
        }) {
            Text("Calcular Minutos")
        }
        Text("Minutos: $minutos")
    }
}

@Composable
fun SumaNumerosSection() {
    var limite by remember { mutableStateOf("") }
    var suma by remember { mutableStateOf(0) }

    Column {
        Text("Calcular Suma de Números")
        TextField(
            value = limite,
            onValueChange = { limite = it },
            label = { Text("Límite") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val lim = limite.toIntOrNull() ?: 0
            suma = (1..lim).sum()
        }) {
            Text("Calcular Suma")
        }
        Text("Suma: $suma")
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EC1joaquinRoqueTheme {
        AppContent()
    }
}