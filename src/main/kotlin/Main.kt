package net.raphdf201.adventofcode2025

import kotlin.time.measureTime

fun main() {
    val debug = readln("Debug : ") == "y"
    val day = readln("Day : ").toInt()
    val input = getInput(day)
    var output: Pair<Any?, Any?> = Pair(null, null)
    val time = measureTime {
        output = functions[day - 1](input, debug)
    }
    println("Day $day in ${time.inWholeMilliseconds} ms")
    println("Part 1 : ${output.first}")
    println("Part 2 : ${output.second}")
}

val functions = listOf<(Pair<List<String>, List<String>>, Boolean) -> Pair<Any, Any>>(
    { (i, di), d ->// 1
        if (d) Pair(
            dayOnePartOne(di.map { input -> (input.first() == 'R') to input.substring(1).toInt() }),
            dayOnePartTwo(di.map { input -> (input.first() == 'R') to input.substring(1).toInt() })
        ) else Pair(
            dayOnePartOne(i.map { input -> (input.first() == 'R') to input.substring(1).toInt() }),
            dayOnePartTwo(i.map { input -> (input.first() == 'R') to input.substring(1).toInt() })
        )
    },
    { (i, di), d ->// 2
        if (d) Pair(
            dayTwoPartOne(di[0].split(",")),
            dayTwoPartTwo(di[0].split(","))
        ) else Pair(
            dayTwoPartOne(i[0].split(",")),
            dayTwoPartTwo(i[0].split(","))
        )
    },
    { (i, di), d ->// 3
        if (d) Pair(
            dayThreePartOne(di),
            dayThreePartTwo(di)
        ) else Pair(
            dayThreePartOne(i),
            dayThreePartTwo(i)
        )
    },
    { (i, di), d ->// 4
        if (d) Pair(
            dayFourPartOne(di),
            dayFourPartTwo(di)
        ) else Pair(
            dayFourPartOne(i),
            dayFourPartTwo(i)
        )
    },
    { i, d ->
        Pair(
            dayFivePartOne(i),
            dayFivePartTwo(i.first)
        )
    },
    { (i, di), d ->
        if (d) Pair(
            daySixPartOne(di),
            daySixPartTwo(di)
        ) else Pair(
            daySixPartOne(i),
            daySixPartTwo(i)
        )
    },
)
