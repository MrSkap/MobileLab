package com.example.mobileapp

import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("{word}")
    fun getWord(@Path("word") word: String): ArrayList<Word>
}