package net.raphdf201.adventofcode2025


fun daySevenPartOne(input: List<String>): Int {
    var start = 0
    var total = 0
    val map = input.map { it.toCharArray() }
    input.first().forEachIndexed { i, it -> if (it == 'S') start = i }
    var activeColumns = mutableSetOf(start)

    for (row in 0 until input.size) {
        val nextColumns = mutableSetOf<Int>()

        for (col in activeColumns) {
            if (map[row][col] == '^') {
                total++
                if (col - 1 >= 0) nextColumns.add(col - 1)
                if (col + 1 < map[row].size) nextColumns.add(col + 1)
            } else nextColumns.add(col)
        }

        activeColumns = nextColumns
    }

    return total
}

fun daySevenPartTwo(input: List<String>): Long {
    val total = input.drop(1).fold(
        buildMap {
            input.firstOrNull()?.forEachIndexed { i, c -> if (c == 'S') put(i, 1L) }
        }
    ) { acc, line ->
        buildMap {
            for ((i, n) in acc) {
                if (line.getOrNull(i) == '^') {
                    add(i - 1, n)
                    add(i + 1, n)
                } else {
                    add(i, n)
                }
            }
        }
    }
    return total.values.sum()
}
