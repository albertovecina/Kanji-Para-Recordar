package com.example.kanjipararecordar.domain.model

sealed class Route(val route: String) {
    object KanjiList: Route("KanjiList")
    object KanjiDetail: Route("KanjiDetail")
}