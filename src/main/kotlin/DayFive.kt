package net.raphdf201.adventofcode2025

fun dayFivePartOne(input: Pair<List<String>, List<String>>): ULong {
    var freshIngredients = 0uL

    input.second.forEach num@{ num ->
        input.first.forEach { rangeStr ->
            val range = rangeStr.split("-")
            for (rangeNum in range[0].toULong()..range[1].toULong()) {
                if (rangeNum == num.toULong()) {
                    freshIngredients++
                    break
                }
            }
            return@num
        }
    }

    return freshIngredients
}

fun dayFivePartTwo(input: Pair<List<String>, List<String>>): Int {
    return 0
}
