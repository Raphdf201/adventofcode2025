package net.raphdf201.adventofcode2025

import java.io.File
import kotlin.math.pow

fun getInput(day: Int): Pair<List<String>, List<String>> {
    return Pair(
        File("inputs/$day.txt").readLines(),
        File("inputs/${day}d.txt").readLines()
    )
}

fun Boolean.toInt(): Int = if (this) 1 else 0
fun Int.pow(x: Int): Double = this.toDouble().pow(x)
fun Double.pow(n: Int): Double {
    var result = 1.0
    repeat(n) { result *= this }
    return result
}
fun <K> MutableMap<K, Long>.add(key: K, value: Long) = put(key, getOrElse(key) { 0 } + value)

fun readln(msg: String): String {
    print(msg)
    return readln()
}
