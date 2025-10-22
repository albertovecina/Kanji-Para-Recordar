package com.example.kanjipararecordar.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanjipararecordar.detail.domain.SearchKanjiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanjiDetailViewModel @Inject constructor(private val searchKanjiUseCase: SearchKanjiUseCase) : ViewModel() {

    fun sendIntent() {
        viewModelScope.launch {
            searchKanjiUseCase("casa")?.let {
                Log.d("Kanji", it.toString())
            }
        }
    }
}