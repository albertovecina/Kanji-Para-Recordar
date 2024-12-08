package com.example.kanjipararecordar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kanjipararecordar.domain.model.Kanji
import com.example.kanjipararecordar.domain.usecase.GetKanjiListUseCase

class MainViewModel(
    private val getKanjiListUseCase: GetKanjiListUseCase
) : ViewModel() {

    private val _kanjiList = MutableLiveData<List<Kanji>>(getKanjiListUseCase())
    val kanjiList: LiveData<List<Kanji>> = _kanjiList

    private val _query: MutableLiveData<String> = MutableLiveData<String>("")
    val query: LiveData<String> = _query

    fun onQueryChange(query: String) {
        _query.value = query
        if (query.isEmpty())
            _kanjiList.value = getKanjiListUseCase()
        else
            _kanjiList.value = kanjiList.value?.filter { it.meaning.contains(query) || it.character.contains(query) }
    }

    fun onClearIconClick() {
        onQueryChange("")
    }

}