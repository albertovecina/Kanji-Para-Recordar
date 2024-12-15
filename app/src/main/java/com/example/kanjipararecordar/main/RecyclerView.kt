package com.example.kanjipararecordar.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kanjipararecordar.domain.model.Kanji

@Composable
fun KanjiList(modifier: Modifier, kanjiItems: List<Kanji>, onItemClick: (Kanji) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(kanjiItems) { kanji ->
            KanjiView(kanji, onItemClick)
            HorizontalDivider()
        }
    }
}

@Composable
fun KanjiView(kanji: Kanji, onItemClick: (Kanji) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onItemClick(kanji) },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = kanji.character)
        Text(text = kanji.meaning)
    }
}
