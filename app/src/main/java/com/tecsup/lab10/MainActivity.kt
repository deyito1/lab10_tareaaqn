package com.tecsup.lab10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tecsup.lab10.ui.theme.Lab10gfTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock // Cambia a un ícono disponible
import androidx.compose.material3.Icon

data class Locker(val id: Int, val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab10gfTheme {
                // Scaffold principal con lista de casilleros
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LockerList(
                        lockers = listOf(
                            Locker(1, "Locker 1"),
                            Locker(2, "Locker 2"),
                            Locker(3, "Locker 3"),
                            Locker(4, "Locker 4")
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LockerList(lockers: List<Locker>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lockers.size) { index ->
            LockerItem(locker = lockers[index])
        }
    }
}

@Composable
fun LockerItem(locker: Locker) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = locker.name, style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = { /* Acción de abrir el casillero */ }) {
                Icon(Icons.Default.Lock, contentDescription = "Abrir casillero") // Cambié a un ícono disponible
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LockerListPreview() {
    Lab10gfTheme {
        LockerList(
            lockers = listOf(
                Locker(1, "Locker 1"),
                Locker(2, "Locker 2"),
                Locker(3, "Locker 3")
            )
        )
    }
}
