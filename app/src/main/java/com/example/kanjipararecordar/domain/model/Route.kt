package com.example.kanjipararecordar.domain.model

import com.example.kanjipararecordar.core.toJson
import kotlinx.serialization.Serializable

@Serializable
sealed class Route(val route: String) {
    @Serializable
    object MainScreen : Route("main")

    @Serializable
    object KanjiDetail : Route("detail/{kanji}") {
        const val ARG_KANJI = "kanji"
        fun createRoute(kanji: Kanji) = "detail/${kanji.toJson()}"
    }
}