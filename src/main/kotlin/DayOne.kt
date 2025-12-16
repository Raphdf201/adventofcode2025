package net.raphdf201.adventofcode2025

import kotlin.math.abs

fun dayOnePartOne(input: List<Pair<Boolean, Int>>): UInt {
    var pos = 50
    var result = 0u

    input.forEach { (isRight, distance) ->
        if (isRight) pos += distance
        else pos -= distance

        while (pos !in 0..99) {
            if (pos > 99) pos -= 100
            else pos += 100
        }

        if (pos == 0) result++
    }
    return result
}

fun dayOnePartTwo(input: List<Pair<Boolean, Int>>): Int {
    var pos = 50
    var result = 0

    input.forEach { (isRight, distance) ->
        val direction = if (isRight) 1 else -1
        val newPos = Math.floorMod(pos + direction * distance, 100)

        result += abs(Math.floorDiv(pos + direction * distance, 100))

        if (!isRight) {
            result += (newPos == 0).toInt() - (pos == 0).toInt()
        }

        pos = newPos
    }
    return result
}
