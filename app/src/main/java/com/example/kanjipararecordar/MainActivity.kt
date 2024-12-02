package com.example.kanjipararecordar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.usecase.GetKanjiUseCase
import com.example.kanjipararecordar.ui.theme.KanjiParaRecordarTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dataSource = DataSource(this)
        val kanjiList = GetKanjiUseCase(dataSource).invoke()
        setContent {
            var query by rememberSaveable { mutableStateOf("") }
            var active by rememberSaveable { mutableStateOf(false) }
            KanjiParaRecordarTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                ,
                            active = active,
                            onActiveChange = {
                                active = it
                            },
                            onQueryChange = {
                                query = it
                            },
                            onSearch = { },
                            leadingIcon = {
                                if (active)
                                    IconButton(onClick = {
                                        query = ""
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                else
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search"
                                        )
                                    }
                            },
                            query = query
                        ) {
                            val filteredList = kanjiList.filter { it.meaning.contains(query) || it.character.contains(query) }
                            KanjiList(
                                Modifier
                                    .fillMaxSize(), filteredList
                            )
                        }
                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        KanjiList(
                            Modifier
                                .fillMaxSize(), kanjiList
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KanjiParaRecordarTheme {
        Greeting("Android")
    }
}