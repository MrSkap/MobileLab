package com.example.mobileapp

data class Meaning(val partOfSpeech: String,
                   val definitions: ArrayList<Definition>,
                   val synonyms:ArrayList<String>,
                   val antonyms: ArrayList<String> )
