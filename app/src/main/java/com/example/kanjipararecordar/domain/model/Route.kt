package com.example.kanjipararecordar.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
sealed class Route(val route: String) {
    @Serializable
    object MainScreen : Route("main")

    @Serializable
    object KanjiDetail : Route("detail/{kanji}") {
        const val ARG_KANJI = "kanji"
        fun createRoute(kanji: Kanji) = "detail/${Json.encodeToString(kanji)}"
    }
}