package net.raphdf201.adventofcode2025

import kotlin.collections.map
import kotlin.text.split

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

fun dayFivePartTwo(input: List<String>): ULong {
    val merged = mutableListOf<ULongRange>()

    val ranges = input.map { rangeStr ->
        val parts = rangeStr.split("-")
        parts[0].toULong()..parts[1].toULong()
    }.sortedBy { it.first }

    for (range in ranges) {
        if (merged.isEmpty() || merged.last().last < range.first - 1uL) {
            merged.add(range)
        } else {
            val last = merged.removeLast()
            merged.add(last.first..maxOf(last.last, range.last))
        }
    }

    return merged.sumOf { it.last - it.first + 1uL }
}
