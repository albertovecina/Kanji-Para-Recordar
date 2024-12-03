package com.example.kanjipararecordar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kanjipararecordar.domain.model.Route
import com.example.kanjipararecordar.ui.KanjiDetailScreen
import com.example.kanjipararecordar.ui.MainScreen

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            NavHost(navController = navigationController, startDestination = Route.MainScreen.route) {
                composable(Route.MainScreen.route) { MainScreen(navigationController) }
                composable(Route.KanjiDetail.route) { KanjiDetailScreen(navigationController) }
            }
        }
    }
}