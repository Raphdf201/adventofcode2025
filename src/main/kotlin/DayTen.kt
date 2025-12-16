package net.raphdf201.adventofcode2025

fun dayTenPartOne(input: List<String>): ULong {
    var total = 0uL

    input.forEach { machine ->
        val machineParts = machine.split(" ")
        val targetStr = machineParts.first().drop(1).dropLast(1)
        val target = targetStr.foldIndexed(0) { idx, acc, ch ->
            acc or (if (ch == '#') 1 shl idx else 0)
        }

        val buttons = machineParts.drop(1).dropLast(1).mapNotNull { button ->
            if (button.startsWith('(') && button.endsWith(')')) {
                val inner = button.drop(1).dropLast(1)
                var buttonBits = 0
                inner.split(",").forEach { numStr ->
                    buttonBits = buttonBits or (1 shl numStr.toInt())
                }
                buttonBits
            } else null
        }

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(0 to 0)
        val best = IntArray(1 shl targetStr.length) { Int.MAX_VALUE }
        best[0] = 0

        while (queue.isNotEmpty()) {
            val (state, presses) = queue.removeFirst()

            for (button in buttons) {
                val newState = state xor button
                val newPresses = presses + 1

                if (newState == target) {
                    total += newPresses.toULong()
                    queue.clear()
                    break
                } else if (newPresses < best[newState]) {
                    best[newState] = newPresses
                    queue.add(newState to newPresses)
                }
            }
        }
    }

    return total
}

fun dayTenPartTwo(input: List<String>) {

}
