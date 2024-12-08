package com.example.kanjipararecordar.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Kanji(val character: String, val meaning: String) : Parcelable