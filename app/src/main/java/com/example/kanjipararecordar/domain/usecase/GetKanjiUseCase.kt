package com.example.kanjipararecordar.domain.usecase

import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.model.Kanji

class GetKanjiUseCase(private val dataSource: DataSource) {

    operator fun invoke(): List<Kanji> =
        dataSource.getKanji().map { Kanji(character = it.value, meaning = it.key) }
}