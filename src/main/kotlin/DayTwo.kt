package net.raphdf201.adventofcode2025

fun dayTwoPartOne(input: List<String>): ULong {
    var total = 0uL

    input.forEach { range ->
        val edges = range.split("-")

        for (num in edges[0].toULong()..edges[1].toULong()) {
            if (Regex("(.+)\\1").matches(num.toString())) {
                total += num
            }
        }
    }

    return total
}

fun dayTwoPartTwo(input: List<String>): ULong {
    var total = 0uL

    input.forEach { range ->
        val edges = range.split("-")

        for (num in edges[0].toULong()..edges[1].toULong()) {
            if (Regex("(\\d+)\\1+").matches(num.toString())) {
                total += num
            }
        }
    }

    return total
}
