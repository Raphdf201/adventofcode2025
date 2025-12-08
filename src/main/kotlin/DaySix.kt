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

fun daySixPartTwo(input: List<String>): UInt {
    return 0u
}
