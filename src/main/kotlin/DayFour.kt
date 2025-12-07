package net.raphdf201.adventofcode2025

fun dayFourPartOne(input: List<String>): Int {
    var total = 0

    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] != '@') continue

            var papers = 0

            for (rowOffset in -1..1) {
                for (colOffset in -1..1) {
                    val innerRow = row + rowOffset
                    val innerCol = col + colOffset
                    if (innerRow in input.indices && innerCol in input[innerRow].indices) {
                        if (input[innerRow][innerCol] == '@') papers++
                    }
                }
            }

            if (papers < 5) total++
        }
    }

    return total
}

fun dayFourPartTwo(input: List<String>): Int {
    return removeRolls(input.map { it.toCharArray() })
}

private fun removeRolls(input: List<CharArray>): Int {
    var removedThisRound = 0

    for (row in input.indices) {
        for (col in input.indices) {
            if (input[row][col] != '@') continue
            var papers = 0

            for (rowOffset in -1..1) {
                for (colOffset in -1..1) {
                    val innerRow = row + rowOffset
                    val innerCol = col + colOffset
                    if (innerRow in input.indices && innerCol in input[innerRow].indices) {
                        if (input[innerRow][innerCol] == '@') papers++
                    }
                }
            }

            if (papers < 5) {
                removedThisRound++
                input[row][col] = '.'
            }
        }
    }

    if (removedThisRound == 0) return 0
    return removedThisRound + removeRolls(input)
}
