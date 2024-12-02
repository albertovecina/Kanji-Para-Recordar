package com.example.kanjipararecordar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.usecase.GetKanjiUseCase
import com.example.kanjipararecordar.ui.theme.KanjiParaRecordarTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*val navigationController = rememberNavController()
            NavHost(navController = navigationController, startDestination = Route.KanjiList.route) {

            }*/

            val dataSource = DataSource(this)
            val kanjiList = GetKanjiUseCase(dataSource).invoke()
            var query by rememberSaveable { mutableStateOf("") }
            KanjiParaRecordarTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val context = LocalContext.current
                        SearchBar(modifier = Modifier
                            .fillMaxWidth(),
                            expanded = false,
                            onExpandedChange = {
                                Toast.makeText(context, "EXPANDED", Toast.LENGTH_SHORT).show()
                            },
                            inputField = {
                                SearchBarDefaults.InputField(
                                    query = query,
                                    onQueryChange = { query = it },
                                    onSearch = { },
                                    expanded = false,
                                    leadingIcon = {
                                        IconButton({}) {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = "Search"
                                            )
                                        }
                                    },
                                    trailingIcon = {
                                        IconButton({ query = "" }) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Clear"
                                            )
                                        }
                                    },
                                    onExpandedChange = {

                                    }
                                )
                            }, content = {})
                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        KanjiList(
                            Modifier
                                .fillMaxSize(), kanjiList.filter { it.meaning.contains(query) }
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun temp() {
    val dataSource = DataSource(LocalContext.current)
    val kanjiList = GetKanjiUseCase(dataSource).invoke()
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
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
}

@Composable
fun SearchLeadingIcon(expanded: Boolean, onSearchClick: () -> Unit = {}, onBackClick: () -> Unit = {}) {
    if (expanded)
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    else
        IconButton(onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
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