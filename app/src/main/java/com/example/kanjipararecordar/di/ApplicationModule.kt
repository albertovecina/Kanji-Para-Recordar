package com.example.kanjipararecordar.di

import com.example.kanjipararecordar.data.network.JishoApiInterface
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder()
        .baseUrl("https://jisho.org/api/v1/")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun providesJishoApiInterface(retrofit: Retrofit): JishoApiInterface = retrofit.create(JishoApiInterface::class.java)

}