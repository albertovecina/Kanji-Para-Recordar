package com.example.kanjipararecordar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kanjipararecordar.domain.model.Kanji

@Composable
fun KanjiList(modifier: Modifier, kanjiItems: List<Kanji>) {
    LazyColumn(modifier = modifier) {
        items(kanjiItems) { kanji ->
            KanjiView(kanji)

        }
    }
}

@Composable
fun KanjiView(kanji: Kanji) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = kanji.character)
            Text(text = kanji.meaning)
        }
    }
}
