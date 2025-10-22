package com.example.kanjipararecordar.data.network

import com.example.kanjipararecordar.data.jisho.SearchResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JishoApiInterface {

    @GET("search/words")
    fun search(@Query("keyword") keyword:String): Call<SearchResponseEntity>

}