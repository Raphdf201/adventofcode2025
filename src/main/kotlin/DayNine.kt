package net.raphdf201.adventofcode2025

import kotlin.math.abs
import kotlin.math.max

fun dayNinePartOne(input: List<Pair<Int, Int>>): ULong {
    var total = 0UL

    for (i in input.indices) {
        for (j in i + 1 until input.size) {
            val (x1, y1) = input[i]
            val (x2, y2) = input[j]

            val width = abs(x2 - x1).toULong() + 1UL
            val height = abs(y2 - y1).toULong() + 1UL
            val area = width * height

            total = max(total, area)
        }
    }

    return total
}

fun dayNinePartTwo(input: List<Pair<Int, Int>>): ULong {
    val points = input.map { DayNinePoint(it.first, it.second) }

    val horizontalLinesByY = mutableMapOf<Int, MutableList<Line>>()
    val verticalLinesByX = mutableMapOf<Int, MutableList<Line>>()

    for (i in points.indices) {
        val p1 = points[i]
        val p2 = points[(i + 1) % points.size]

        if (p1.x == p2.x) {
            verticalLinesByX.getOrPut(p1.x) { mutableListOf() }
                .add(Line(minOf(p1.y, p2.y), maxOf(p1.y, p2.y)))
        } else {
            horizontalLinesByY.getOrPut(p1.y) { mutableListOf() }
                .add(Line(minOf(p1.x, p2.x), maxOf(p1.x, p2.x)))
        }
    }

    var highestArea = 0uL

    for (lowerIdx in points.indices) {
        val lowerPoint = points[lowerIdx]

        higherLoop@ for (higherIdx in (lowerIdx + 1) until points.size) {
            val higherPoint = points[higherIdx]
            val thisArea = lowerPoint.rectangleSize(higherPoint)

            if (thisArea > highestArea) {
                val rect = Rectangle.fromExclusive(lowerPoint, higherPoint)

                if (rect.upperLeft.y > rect.lowerRight.y) {
                    continue@higherLoop
                }

                for (y in rect.upperLeft.y..rect.lowerRight.y) {
                    horizontalLinesByY[y]?.let { lines ->
                        for (line in lines) {
                            if (line.isInsideRectHorizontal(rect)) {
                                continue@higherLoop
                            }
                        }
                    }
                }

                if (rect.upperLeft.x > rect.lowerRight.x) {
                    continue@higherLoop
                }

                for (x in rect.upperLeft.x..rect.lowerRight.x) {
                    verticalLinesByX[x]?.let { lines ->
                        for (line in lines) {
                            if (line.isInsideRectVertical(rect)) {
                                continue@higherLoop
                            }
                        }
                    }
                }

                highestArea = thisArea
            }
        }
    }

    return highestArea
}

private data class DayNinePoint(val x: Int, val y: Int) {
    fun rectangleSize(other: DayNinePoint): ULong {
        val width = (x.toLong() - other.x.toLong()).let { if (it < 0) -it else it } + 1
        val height = (y.toLong() - other.y.toLong()).let { if (it < 0) -it else it } + 1
        return (width * height).toULong()
    }
}

private data class Line(val start: Int, val end: Int) {
    fun isInsideRectHorizontal(rect: Rectangle): Boolean {
        return start < rect.lowerRight.x && end > rect.upperLeft.x
    }

    fun isInsideRectVertical(rect: Rectangle): Boolean {
        return start < rect.lowerRight.y && end > rect.upperLeft.y
    }
}

private data class Rectangle(val upperLeft: DayNinePoint, val lowerRight: DayNinePoint) {
    companion object {
        fun fromExclusive(p1: DayNinePoint, p2: DayNinePoint): Rectangle {
            val minX = minOf(p1.x, p2.x)
            val maxX = maxOf(p1.x, p2.x)
            val minY = minOf(p1.y, p2.y)
            val maxY = maxOf(p1.y, p2.y)

            return Rectangle(
                upperLeft = DayNinePoint(minX + 1, minY + 1),
                lowerRight = DayNinePoint(maxX - 1, maxY - 1)
            )
        }
    }
}
