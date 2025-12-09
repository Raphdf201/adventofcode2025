package net.raphdf201.adventofcode2025

import java.io.File

fun getInput(day: Int): Pair<List<String>, List<String>> {
    return when(day) {
        5 -> Pair(
            File("inputs/$day-1.txt").readLines(),
            File("inputs/$day-2.txt").readLines()
        )
        else -> Pair(
            File("inputs/$day.txt").readLines(),
            File("inputs/${day}d.txt").readLines()
        )
    }
}

fun Boolean.toInt(): Int = if (this) 1 else 0

fun <K> MutableMap<K, Long>.add(key: K, value: Long) {
    put(key, getOrElse(key) { 0 } + value)
}

fun readln(msg: String): String {
    print(msg)
    return readln()
}
