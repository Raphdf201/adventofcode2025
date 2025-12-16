package net.raphdf201.adventofcode2025

import java.util.*

fun dayThreePartOne(input: List<String>): UInt {
    var total = 0u

    input.forEach { batteryBank ->
        val nums = batteryBank.map { it.digitToInt() }
        var largest = 0u
        var first = 0
        var last = 0

        while (last != nums.size - 1) {
            last++
            if ((nums[last] > nums[first]) && last != nums.size - 1) {
                first = last
                continue
            }

            val j = "${nums[first]}${nums[last]}".toUInt()
            if (j > largest) largest = j
        }
        total += largest
    }

    return total
}

fun dayThreePartTwo(input: List<String>): ULong {
    var total = 0uL

    input.forEach { batteryBank ->
        val nums = batteryBank.map { it.digitToInt() }.toMutableList()
        val stack = Stack<Int>()

        stack.push(nums.removeFirst())

        while (nums.isNotEmpty()) {
            val value = nums.removeFirst()

            while (stack.isNotEmpty() && stack.peek() < value && nums.size + stack.size >= 12) stack.pop()
            stack.push(value)
        }

        total += stack.take(12).joinToString("").toULong()
    }

    return total
}
