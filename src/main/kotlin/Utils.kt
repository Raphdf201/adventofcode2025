package net.raphdf201.adventofcode2025

import java.io.File

fun getInput(day: Int, debug: Boolean): List<String> {
    return if (debug) File("inputs/${day}d.txt").readLines()
    else File("inputs/$day.txt").readLines()
}

fun Double.notNaNOrNull(): Double? {
    if (this.isNaN()) return null
    return this
}
