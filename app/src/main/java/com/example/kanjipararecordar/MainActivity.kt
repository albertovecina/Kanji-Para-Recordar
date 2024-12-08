package com.example.kanjipararecordar

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.model.Kanji
import com.example.kanjipararecordar.domain.model.Route
import com.example.kanjipararecordar.domain.usecase.GetKanjiListUseCase
import com.example.kanjipararecordar.ui.KanjiDetailScreen
import com.example.kanjipararecordar.main.MainScreen
import com.example.kanjipararecordar.main.MainViewModel
import com.example.kanjipararecordar.util.getCompatParcelable
import kotlinx.serialization.json.Json

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            val dataSource = DataSource(LocalContext.current)
            val getKanjiListUseCase = GetKanjiListUseCase(dataSource)
            NavHost(navController = navigationController, startDestination = Route.MainScreen) {
                composable<Route.MainScreen> { MainScreen(MainViewModel(getKanjiListUseCase), navigationController) }

                composable(
                    route = Route.KanjiDetail.route, arguments = listOf(
                        navArgument(
                            name = Route.KanjiDetail.ARG_KANJI,
                            builder = { type = parcelableType<Kanji>() },
                        )
                    )
                ) { backStackEntry ->
                    val kanji: Kanji = backStackEntry.arguments?.getCompatParcelable(Route.KanjiDetail.ARG_KANJI) ?: Kanji("", "")
                    KanjiDetailScreen(navigationController, kanji)
                }
            }
        }
    }
}

inline fun <reified T : Parcelable> parcelableType(isNullableAllowed: Boolean = false) = object : NavType<T>(isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? = bundle.getCompatParcelable(key)

    override fun parseValue(value: String): T = Json.decodeFromString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}