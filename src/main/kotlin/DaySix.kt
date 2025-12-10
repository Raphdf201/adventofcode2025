package net.raphdf201.adventofcode2025

fun daySixPartOne(input: List<String>): ULong {
    var total = 0uL
    val list = mutableListOf<MutableList<ULong>>()

    input.forEach {
        for ((i, num) in it.split(Regex("\\s+")).withIndex()) {
            if (num != "+" && num != "*") {
                if (list.size <= i) list.add(mutableListOf())
                if (num == "") continue
                list[i].add(num.toULong())
            } else {
                total += if (num == "+") {
                    list[i].sum()
                } else {
                    list[i].reduce { acc, value -> acc * value }
                }
            }
        }
    }

    return total
}

fun daySixPartTwo(input: List<String>): ULong {
    var total = 0uL
    val lastLine = input.last()
    val numberLines = input.subList(0, input.size - 1)

    var index = 0
    while (index < numberLines.first().length) {
        val start = index
        var last = index + 1
        val action = lastLine[index]
        if (last < lastLine.length - 1) {
            while (lastLine[last] == ' ') {
                last++
            }
            last--
        } else {
            last++
        }

        val numbers = mutableListOf<ULong>()
        for (i in last - 1 downTo start) {
            var numberString = ""
            for (line in numberLines){
                if (line[i] != ' ') {
                    numberString += line[i]
                }
            }
            numbers.add(numberString.toULong())
        }

        total +=
            if (action == '+') {
                numbers.sum()
            } else {
                numbers.reduce { acc, value -> acc * value }
            }

        index = last + 1
    }

    return total
}
