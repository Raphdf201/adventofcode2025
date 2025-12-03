package net.raphdf201.adventofcode2025

import kotlin.system.measureTimeMillis

fun main() {
    print("Debug (y/n) : ")
    val debug = readln().lowercase() == "y"
    print("Day : ")
    val day = readln().toIntOrNull() ?: throw IllegalArgumentException("Not a valid day")
    val input = getInput(day, debug)
    var output: Pair<Any?, Any?> = Pair(null, null)
    val time = measureTimeMillis {
        output = functions[day - 1](input)
    }
    println("Day $day in $time ms")
    println("Part 1 : ${output.first}")
    println("Part 2 : ${output.second}")
}

val functions = listOf<(List<String>) -> Pair<Any?, Any?>>(
    {
        Pair(
            dayOnePartOne(it.map { input -> (input.first() == 'R') to input.substring(1).toInt() }),
            dayOnePartTwo(it.map { input -> (input.first() == 'R') to input.substring(1).toInt() })
        )
    },
    {
        Pair(
            dayTwoPartOne(it[0].split(",")),
            dayTwoPartTwo(it[0].split(","))
        )
    },
    {
        Pair(
            dayThreePartOne(it),
            dayThreePartTwo(it)
        )
    }
)
