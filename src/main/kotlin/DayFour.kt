package net.raphdf201.adventofcode2025

const val empty = '.'
const val paper = '@'

fun dayFourPartOne(input: List<String>): Int {
    var count = 0

    for (row in input.indices) for (col in input[row].indices) {
        if (hasMoreThan4Papers(input, row, col)) count++
    }

    return count
}

fun dayFourPartTwo(input: List<String>): Int {
    return 0
}

fun hasMoreThan4Papers(input: List<String>, i1: Int, i2: Int): Boolean {
    if (input[i1][i2] == paper) return false

    var count = 0

    for (rowOffset in -1..1) for (colOffset in -1..1) {
        val row = i1 + rowOffset
        val col = i2 + colOffset

        if (row in input.indices && col in input[row].indices) if (input[row][col] == paper) count++
    }

    return count >= 4
}
