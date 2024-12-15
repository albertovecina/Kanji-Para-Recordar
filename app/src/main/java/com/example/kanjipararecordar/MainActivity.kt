package com.example.kanjipararecordar

import android.content.ContextWrapper
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kanjipararecordar.core.fromJson
import com.example.kanjipararecordar.detail.KanjiDetailViewModel
import com.example.kanjipararecordar.detail.domain.SearchKanjiUseCase
import com.example.kanjipararecordar.detail.ui.KanjiDetailScreen
import com.example.kanjipararecordar.di.ApplicationModule
import com.example.kanjipararecordar.domain.model.Kanji
import com.example.kanjipararecordar.domain.model.Route
import com.example.kanjipararecordar.main.MainScreen
import com.example.kanjipararecordar.util.getCompatParcelable
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getKanjiUseCase: SearchKanjiUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            val detailViewModel: KanjiDetailViewModel = KanjiDetailViewModel(getKanjiUseCase)
            NavHost(navController = navigationController, startDestination = Route.MainScreen) {
                composable<Route.MainScreen> { MainScreen(navigationController) }

                composable(
                    route = Route.KanjiDetail.route, arguments = listOf(
                        navArgument(
                            name = Route.KanjiDetail.ARG_KANJI,
                            builder = { type = parcelableType<Kanji>() },
                        )
                    )
                ) { backStackEntry ->
                    val kanji: Kanji = backStackEntry.arguments?.getCompatParcelable(Route.KanjiDetail.ARG_KANJI) ?: Kanji("", "")
                    KanjiDetailScreen(navigationController, kanji, detailViewModel)
                }
            }
        }
    }
}


inline fun <reified T : Parcelable> ContextWrapper.parcelableType(isNullableAllowed: Boolean = false) = object : NavType<T>(isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? = bundle.getCompatParcelable(key)

    override fun parseValue(value: String): T = fromJson(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}