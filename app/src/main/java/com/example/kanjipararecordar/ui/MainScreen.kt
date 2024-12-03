package com.example.kanjipararecordar.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.kanjipararecordar.data.DataSource
import com.example.kanjipararecordar.domain.model.Route
import com.example.kanjipararecordar.domain.usecase.GetKanjiUseCase
import com.example.kanjipararecordar.ui.theme.KanjiParaRecordarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigationController: NavController) {
    KanjiParaRecordarTheme {
        val context = LocalContext.current
        val dataSource = DataSource(context)
        val kanjiList = GetKanjiUseCase(dataSource).invoke()
        var query by rememberSaveable { mutableStateOf("") }
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
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
                        .fillMaxSize(), kanjiList.filter { it.meaning.contains(query) },
                    onItemClick = { navigationController.navigate(Route.KanjiDetail.route) }
                )
            }
        }
    }
}