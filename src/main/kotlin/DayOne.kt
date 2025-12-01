package net.raphdf201.adventofcode2025

fun dayOnePartOne(input: List<String>): Int {
    val processed = input.map { it.first() == 'R' }.zip(input.map { it.subSequence(1, it.length).toString().toInt() })
    var pos = 50
    var result = 0
    processed.forEach {
        if (it.first) pos += it.second
        else pos -= it.second

        while (pos !in 0..99) {
            if (pos > 99) pos -= 100
            else pos += 100
        }

        if (pos == 0) result++
    }
    return result
}

fun dayOnePartTwo(input: List<String>): Int {
    return 0
}
