package net.raphdf201.adventofcode2025

fun dayFivePartOne(input: Pair<List<String>, List<String>>): ULong {
    var total = 0uL

    val ranges = input.first.map { rangeStr ->
        val parts = rangeStr.split("-")
        parts[0].toULong()..parts[1].toULong()
    }

    input.second.forEach { num ->
        val ingredientId = num.toULong()
        if (ranges.any { range -> ingredientId in range }) {
            total++
        }
    }

    return total
}

fun dayFivePartTwo(input: Pair<List<String>, List<String>>): Int {
    return 0
}
