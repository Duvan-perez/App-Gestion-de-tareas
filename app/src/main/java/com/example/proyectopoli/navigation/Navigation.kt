package com.example.proyectopoli.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentNavigation() { // Estado para controlar el contenido seleccionado
    var contenido by remember { mutableStateOf("Menu") }

    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(

                modifier = Modifier
                    .width(150.dp)
            ) {
                DrawerMenuItem(label = "Perfil") { contenido = "Perfil" }
                DrawerMenuItem(label = "Fotos") { contenido = "Fotos" }
                DrawerMenuItem(label = "Videos") { contenido = "Videos" }
                DrawerMenuItem(label = "Web") { contenido = "Web" }
                DrawerMenuItem(label = "Botones") { contenido = "Botones" }
            }
        }
    ) {
        Scaffold(

            topBar = {
                TopAppBar(

                    title = { Text("Gestión de Tareas") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent // Desactiva el color del tema
                            ),
                    modifier = Modifier
                        .background(Color(0xFF28adc8)) // <-- Ahora sí aplicará
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFF95cbd9))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when (contenido) {
                    "Perfil" -> PerfilFragment()
                    "Fotos" -> FotosFragment()
                    "Videos" -> VideosFragment()
                    "Web" -> WebFragment()
                    "Botones" -> BotonesFragment()
                }
            }
        }
    }
}
@Composable
fun DrawerMenuItem(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
}
@Preview(showBackground = true)
@Composable
fun ContentNavigationPreview() {
    ContentNavigation()
}