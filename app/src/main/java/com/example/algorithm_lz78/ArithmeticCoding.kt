package com.example.algorithm_lz78

fun arithmeticEncode(text: String): String {
    if (text.isEmpty()) return ""

    // Подсчитываем частоту символов
    val frequencyMap = text.groupingBy { it }.eachCount().mapValues { it.value.toDouble() / text.length }
    val sortedFrequencyMap = frequencyMap.entries.sortedByDescending { it.value }

    var low = 0.0
    var high = 1.0

    // Проходим по каждому символу в тексте
    for (char in text) {
        val range = high - low
        val (charLow, charHigh) = getRangeForChar(char, sortedFrequencyMap)

        // Обновляем границы диапазона
        high = low + range * charHigh
        low = low + range * charLow
    }
    return low.toString()
}

// Функция для вычисления диапазонов символов на основе их частот
private fun getRangeForChar(char: Char, sortedFrequencyMap: List<Map.Entry<Char, Double>>): Pair<Double, Double> {
    var cumulativeProbability = 0.0

    for ((key, probability) in sortedFrequencyMap) {
        if (key == char) {
            return Pair(cumulativeProbability, cumulativeProbability + probability)
        }
        cumulativeProbability += probability
    }

    throw IllegalArgumentException("Character $char not found in frequency map")
}
