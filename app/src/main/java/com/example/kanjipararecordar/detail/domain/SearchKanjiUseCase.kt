package com.example.kanjipararecordar.detail.domain

import com.example.kanjipararecordar.data.jisho.SearchResponseEntity
import com.example.kanjipararecordar.data.network.JishoApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchKanjiUseCase @Inject constructor(private val jishoApiInterface: JishoApiInterface) {
    suspend operator fun invoke(keyword: String): SearchResponseEntity? {
        return withContext(Dispatchers.IO) {
            jishoApiInterface.search(keyword).execute().body()
        }
    }
}