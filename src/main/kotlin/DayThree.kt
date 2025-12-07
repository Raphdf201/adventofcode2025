package net.raphdf201.adventofcode2025

fun dayThreePartOne(input: List<String>): UInt {
    var total = 0u

    input.forEach { batteryBank ->
        val numbers = batteryBank.map { it.digitToInt() }
        var largest = 0u
        var head = 0
        var tail = 0

        while (tail != numbers.size - 1) {
            tail++
            if ((numbers[tail] > numbers[head]) && tail != numbers.size - 1) {
                head = tail
                continue
            }

            val j = "${numbers[head]}${numbers[tail]}".toUInt()
            if (j > largest) largest = j
        }
        total += largest
    }

    return total
}

fun dayThreePartTwo(input: List<String>): Int {
    return 0
}
