package net.raphdf201.adventofcode2025

fun dayNinePartOne(input: List<Pair<Int, Int>>): Int {
    var total = 0

    for (i in input.indices) {
        for (j in i + 1 until input.size) {
            val (x1, y1) = input[i]
            val (x2, y2) = input[j]

            val width = kotlin.math.abs(x2 - x1) + 1
            val height = kotlin.math.abs(y2 - y1) + 1
            val area = width * height

            total = kotlin.math.max(total, area)
        }
    }

    return total
}

fun dayNinePartTwo(input: List<Pair<Int, Int>>): Int {
    return 0
}
