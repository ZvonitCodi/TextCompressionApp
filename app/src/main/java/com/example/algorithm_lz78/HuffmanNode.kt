package com.example.algorithm_lz78

data class HuffmanNode(
    val char: Char? = null,
    val freq: Int,
    val left: HuffmanNode? = null,
    val right: HuffmanNode? = null
)
