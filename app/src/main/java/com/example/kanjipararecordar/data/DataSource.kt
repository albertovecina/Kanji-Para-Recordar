package com.example.kanjipararecordar.data

import android.content.Context
import java.io.InputStream
import kotlin.to

class DataSource(private val context: Context) {

    val file1: String = "kanji1-1200.csv"
    val file2: String = "kanji1201-2200.csv"

    fun getKanji(): Map<String, String> = context.assets.open(file1).readCsv() + context.assets.open(file2).readCsv()

    fun InputStream.readCsv(): Map<String, String> = reader().use {
        it.readLines().associate { line ->
            line.split(",").let {
                it[0] to it[1]
            }
        }
    }

}