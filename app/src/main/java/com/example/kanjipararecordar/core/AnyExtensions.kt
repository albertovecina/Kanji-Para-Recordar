package com.example.kanjipararecordar.core

import com.google.gson.Gson

val gson: Gson by lazy { Gson() }

fun <T> T.toJson(): String = gson.toJson(this)

inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
