package net.raphdf201.adventofcode2025

fun dayFivePartOne(inp: List<String>): ULong {
    var total = 0uL
    var a = 0
    inp.forEachIndexed { i, it -> if (it == "SPLIT") a = i }
    val input = Pair(inp.subList(0, a), inp.subList(a + 1, inp.size))

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

fun dayFivePartTwo(inp: List<String>): ULong {
    val merged = mutableListOf<ULongRange>()
    var a = 0
    inp.forEachIndexed { i, it -> if (it == "SPLIT") a = i }
    val input = inp.subList(0, a)

    val ranges = input.map { rangeStr ->
        val parts = rangeStr.split("-")
        parts[0].toULong()..parts[1].toULong()
    }.sortedBy { it.first }

    for (range in ranges) {
        if (merged.isEmpty() || merged.last().last < range.first - 1uL) merged.add(range)
        else {
            val last = merged.removeLast()
            merged.add(last.first..maxOf(last.last, range.last))
        }
    }

    return merged.sumOf { it.last - it.first + 1uL }
}
