package net.raphdf201.adventofcode2025

fun dayFourPartOne(input: List<String>): Int {
    var count = 0

    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] != '@') {
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

                if (papers < 4) count++
            }
        }
    }

    return count
}

fun dayFourPartTwo(input: List<String>): Int {
    return 0
}
