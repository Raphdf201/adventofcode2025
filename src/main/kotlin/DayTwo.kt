package net.raphdf201.adventofcode2025

fun dayTwoPartOne(input: String): ULong {
    val ranges = input.split(",")
    var total = 0uL

    ranges.forEach { range ->
        val edges = range.split("-")
        for (num in edges[0].toULong()..edges[1].toULong()) {
            if (Regex("(.+)\\1").matches(num.toString())) {
                total += num
            }
        }
    }

    return total
}

fun dayTwoPartTwo(input: String): ULong {
    return 0uL
}
