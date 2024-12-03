package com.example.kanjipararecordar.domain.model

sealed class Route(val route: String) {
    object MainScreen: Route("MainScreen")
    object KanjiDetail: Route("KanjiDetail")
}