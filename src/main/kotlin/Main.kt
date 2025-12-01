package net.raphdf201.adventofcode2025

import kotlin.system.measureTimeMillis

fun main() {
    print("Debug (y/n) : ")
    val debug = readln().lowercase() == "y"
    print("Day : ")
    val day = readln().toInt()
    val input = getInput(day, debug)
    var output: Pair<Any?, Any?> = Pair(null, null)
    val time = measureTimeMillis {
        output = functions[day - 1](input)
    }
    println("Day 1 in $time ms")
    println("Part 1 : ${output.first}")
    println("Part 2 : ${output.second}")
}

val functions = listOf<(List<String>) -> Pair<Any?, Any?>>(
    {
        Pair(dayOnePartOne(it), dayOnePartTwo(it))
    }
)
