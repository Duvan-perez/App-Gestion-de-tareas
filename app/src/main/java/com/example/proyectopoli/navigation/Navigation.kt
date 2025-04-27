package com.example.proyectopoli.navigation

import FotosFragment
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.proyectopoli.screens.HomeScreen
import com.example.proyectopoli.screens.fragments.content.BotonesFragment

import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("perfil") { PerfilFragment(navController) }
        composable("fotos") { FotosFragment(navController) }
        composable("videos") { VideosFragment(navController) }
        composable("web") { WebFragment() }
        composable("botones") { BotonesFragment() }
    }
}