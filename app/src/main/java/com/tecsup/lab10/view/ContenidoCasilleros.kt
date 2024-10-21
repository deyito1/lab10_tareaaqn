package com.tecsup.lab10.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tecsup.lab10.data.CasilleroApiService
import com.tecsup.lab10.data.CasilleroModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

@Composable
fun ContenidoCasilleroEditar(navController: NavHostController, servicio: CasilleroApiService, id: String) {
    var casillero by remember { mutableStateOf<CasilleroModel?>(null) }
    var location by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(id) {
        // Obtener el casillero por su ID
        casillero = servicio.obtenerCasillero(id).body()
        casillero?.let {
            location = it.location
            price = it.price.toString()
            description = it.description
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = location, onValueChange = { location = it }, label = { Text("Ubicación") })
        TextField(value = price, onValueChange = { price = it }, label = { Text("Precio") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })

        Button(onClick = {
            val updatedCasillero = CasilleroModel(id, location, price.toDouble(), description, false, false, false, "")
            coroutineScope.launch {
                servicio.actualizarCasillero(id, updatedCasillero)
                navController.navigate("casilleros")
            }
        }) {
            Text("Guardar")
        }
    }
}

@Composable
fun ContenidoCasilleros(navController: NavHostController, servicio: CasilleroApiService) {
    var listaCasilleros by remember { mutableStateOf(listOf<CasilleroModel>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Carga inicial de los casilleros
        listaCasilleros = servicio.obtenerCasilleros()
    }

    LazyColumn {
        items(listaCasilleros) { casillero ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = casillero.id, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.2f))
                Text(text = casillero.location, modifier = Modifier.weight(0.6f))
                IconButton(
                    onClick = {
                        navController.navigate("casilleroEditar/${casillero.id}")
                    },
                    modifier = Modifier.weight(0.1f)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            servicio.eliminarCasillero(casillero.id)
                            // Actualiza la lista después de eliminar
                            listaCasilleros = servicio.obtenerCasilleros()
                        }
                    },
                    modifier = Modifier.weight(0.1f)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
