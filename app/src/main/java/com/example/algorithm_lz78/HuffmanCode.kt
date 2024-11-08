package com.example.algorithm_lz78

import java.util.PriorityQueue

fun huffmanEncode(text: String): String {
    val root = buildHuffmanTree(text) ?: return ""
    val codeMap = buildHuffmanCodeMap(root)

    // Формируем строку с уникальными символами и их кодами
    return codeMap.entries.joinToString(" ") { "(${it.key}: ${it.value})" }
}

private fun buildHuffmanTree(text: String): HuffmanNode? {
    val frequencyMap = text.groupingBy { it }.eachCount()
    val priorityQueue = PriorityQueue<HuffmanNode>(compareBy { it.freq })

    frequencyMap.forEach { (char, freq) ->
        priorityQueue.add(HuffmanNode(char, freq))
    }

    while (priorityQueue.size > 1) {
        val left = priorityQueue.poll()
        val right = priorityQueue.poll()
        val sum = left.freq + right.freq
        priorityQueue.add(HuffmanNode(freq = sum, left = left, right = right))
    }

    return priorityQueue.poll()
}

private fun buildHuffmanCodeMap(
    root: HuffmanNode?,
    code: String = "",
    codeMap: MutableMap<Char, String> = mutableMapOf()
): Map<Char, String> {
    if (root == null) return codeMap
    root.char?.let { codeMap[it] = code }
    buildHuffmanCodeMap(root.left, code + "0", codeMap)
    buildHuffmanCodeMap(root.right, code + "1", codeMap)
    return codeMap
}
