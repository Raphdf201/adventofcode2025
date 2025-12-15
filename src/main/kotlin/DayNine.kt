package net.raphdf201.adventofcode2025

import kotlin.math.abs
import kotlin.math.max

fun dayNinePartOne(input: List<Pair<Int, Int>>): ULong {
    var total = 0UL

    for (i in input.indices) {
        for (j in i + 1 until input.size) {
            val (x1, y1) = input[i]
            val (x2, y2) = input[j]

            val width = abs(x2 - x1).toULong() + 1UL
            val height = abs(y2 - y1).toULong() + 1UL
            val area = width * height

            total = max(total, area)
        }
    }

    return total
}

fun dayNinePartTwo(input: List<Pair<Int, Int>>): Int {
    return 0
}
