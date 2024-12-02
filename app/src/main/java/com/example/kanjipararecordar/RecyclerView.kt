package com.example.kanjipararecordar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kanjipararecordar.domain.model.Kanji

@Composable
fun KanjiList(modifier: Modifier, kanjiItems: List<Kanji>) {
    LazyColumn(modifier = modifier) {
        items(kanjiItems) { kanji ->
            KanjiView(kanji)
            HorizontalDivider()
        }
    }
}

@Composable
fun KanjiView(kanji: Kanji) {
    Row(Modifier.fillMaxWidth().height(50.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        Text(text = kanji.character)
        Text(text = kanji.meaning)
    }
}
