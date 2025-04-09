package com.example.proyectopoli.screens.fragments.content.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectopoli.model.MenuItem

@Composable
fun MenuFragment(
    onOptionSelected: (String) -> Unit
) {
    val menuItems = listOf(
        MenuItem(id = "perfil", title = "Perfil", icon = Icons.Default.AccountCircle),
        MenuItem(id = "fotos", title = "Fotos", icon = Icons.Default.Image),
        MenuItem(id = "videos", title = "Videos", icon = Icons.Default.Videocam),
        MenuItem(id = "web", title = "Web", icon = Icons.Default.Language),
        MenuItem(id = "botones", title = "Botones", icon = Icons.Default.RadioButtonChecked)
    )

    var selectedOption by remember { mutableStateOf(menuItems.first().id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9F5F9)) // Fondo moderno azul claro
            .padding(16.dp)
    ) {
        Text(
            text = "ProyectoPOLI",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E3A8A) // Azul oscuro
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Divider(color = Color(0xFFB6D5E2))

        Spacer(modifier = Modifier.height(8.dp))

        menuItems.forEach { item ->
            val isSelected = selectedOption == item.id

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        selectedOption = item.id
                        onOptionSelected(item.id)
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        Color(0xFFB3E5FC) // Azul claro para seleccionado
                    else
                        Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(14.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected)
                            Color(0xFF0D47A1) // Azul fuerte
                        else
                            Color(0xFF607D8B) // Gris azulado
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected)
                                Color(0xFF0D47A1)
                            else
                                Color(0xFF37474F)
                        )
                    )
                }
            }
        }
    }
}
