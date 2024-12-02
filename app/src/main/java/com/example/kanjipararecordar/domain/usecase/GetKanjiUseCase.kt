package com.example.kanjipararecordar.domain.usecase

import android.content.Context
import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.model.Kanji

class GetKanjiUseCase(private val dataSource: DataSource) {

    operator fun invoke(): List<Kanji> =
        dataSource.getKanji().map { Kanji(it.key, it.value) }
}