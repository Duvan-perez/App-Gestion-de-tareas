package com.example.proyectopoli.screens.fragments.content.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectopoli.model.MenuItem
import com.example.proyectopoli.ui.theme.components.DrawerItem

@Composable
fun MenuFragment(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val menuItems = listOf(
        MenuItem(id = "perfil", title = "Perfil", icon = Icons.Default.AccountCircle),
        MenuItem(id = "fotos", title = "Fotos", icon = Icons.Default.Image),
        MenuItem(id = "videos", title = "Videos", icon = Icons.Default.Videocam),
        MenuItem(id = "web", title = "Web", icon = Icons.Default.Language),
        MenuItem(id = "botones", title = "Botones", icon = Icons.Default.RadioButtonChecked)
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "ProyectoPOLI",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        Divider()

        LazyColumn {
            items(menuItems) { item ->
                DrawerItem(
                    item = item,
                    selected = selectedOption == item.id,
                    onItemClick = { onOptionSelected(item.id) }
                )
            }
        }
    }
}