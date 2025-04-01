package com.example.proyectopoli.navigation

import androidx.compose.runtime.Composable
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment

@Composable
fun ContentNavigation(selectedOption: String) {
    when (selectedOption) {
        "perfil" -> PerfilFragment()
        "fotos" -> FotosFragment()
        "videos" -> VideosFragment()
        "web" -> WebFragment()
        "botones" -> BotonesFragment()
        else -> PerfilFragment()
    }
}