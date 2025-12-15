package net.raphdf201.adventofcode2025

fun dayNinePartOne(input: List<String>): Int {
    var total = 0
    val redTiles = input.map { line ->
        val (x, y) = line.split(",").map { it.toInt() }
        Pair(x, y)
    }

    for (i in redTiles.indices) {
        for (j in i + 1 until redTiles.size) {
            val (x1, y1) = redTiles[i]
            val (x2, y2) = redTiles[j]

            val width = kotlin.math.abs(x2 - x1) + 1
            val height = kotlin.math.abs(y2 - y1) + 1
            val area = width * height

            total = kotlin.math.max(total, area)
        }
    }

    return total
}

fun dayNinePartTwo(input: List<String>): Int {
    return 0
}
