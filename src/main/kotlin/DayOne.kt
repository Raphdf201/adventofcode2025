package net.raphdf201.adventofcode2025

import kotlin.math.abs

fun dayOnePartOne(input: List<String>): UInt {
    val processed = input.map { (it.first() == 'R') to it.substring(1).toInt() }
    var pos = 50
    var result = 0u
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
    val processed = input.map { (it.first() == 'R') to it.substring(1).toInt() }
    var pos = 50
    var result = 0
    processed.forEach { (isRight, distance) ->
        val direction = if (isRight) 1 else -1
        val newPos = Math.floorMod(pos + direction * distance, 100)
        result += abs(Math.floorDiv(pos + direction * distance, 100))
        if (!isRight) {
            val endingAtZero = if (newPos == 0) 1 else 0
            val startingAtZero = if (pos == 0) 1 else 0
            result += endingAtZero - startingAtZero
        }
        pos = newPos
    }
    return result
}
