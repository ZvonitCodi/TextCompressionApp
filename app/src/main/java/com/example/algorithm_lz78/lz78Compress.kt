package com.example.algorithm_lz78

fun lz78Compress(input: String): String {
    val dictionary = mutableMapOf<String, Int>()
    var currentPhrase = ""
    var index = 1
    val compressedResult = mutableListOf<Pair<Int, Char?>>()

    for (char in input) {
        val newPhrase = currentPhrase + char
        if (newPhrase !in dictionary) {
            dictionary[newPhrase] = index++
            val prefixIndex = dictionary[currentPhrase] ?: 0
            compressedResult.add(Pair(prefixIndex, char))
            currentPhrase = ""
        } else {
            currentPhrase = newPhrase
        }
    }

    if (currentPhrase.isNotEmpty()) {
        val prefixIndex = dictionary[currentPhrase] ?: 0
        compressedResult.add(Pair(prefixIndex, null))
    }

    return compressedResult.joinToString(" ") { "(${it.first}, ${it.second ?: "null"})" }
}