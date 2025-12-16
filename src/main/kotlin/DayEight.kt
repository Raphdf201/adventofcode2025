package net.raphdf201.adventofcode2025

import kotlin.math.sqrt

fun dayEightPartOne(input: List<DayEightPoint>): Int {
    val edges = mutableListOf<Edge>()
    for (i in input.indices)
        for (j in i + 1 until input.size)
            edges.add(Edge(i, j, input[i] distanceTo input[j]))

    edges.sortBy { it.distance }
    val uf = UnionFind(input.size)
    val edgesToTry = minOf(1000, edges.size)
    for (i in 0 until edgesToTry) {
        val edge = edges[i]
        uf.union(edge.from, edge.to)
    }

    val circuitSizes = mutableMapOf<Int, Int>()
    for (i in input.indices) {
        val root = uf.find(i)
        circuitSizes[root] = (circuitSizes[root] ?: 0) + 1
    }

    val sizes = circuitSizes.values.sortedDescending()

    return sizes[0] * sizes[1] * sizes[2]
}

fun dayEightPartTwo(input: List<DayEightPoint>): Long {  // Changed to Long
    val edges = mutableListOf<Edge>()
    for (i in input.indices)
        for (j in i + 1 until input.size)
            edges.add(Edge(i, j, input[i] distanceTo input[j]))

    edges.sortBy { it.distance }
    val uf = UnionFind(input.size)
    var lastEdge: Edge? = null
    for (edge in edges) if (uf.union(edge.from, edge.to)) {
        lastEdge = edge
        if (uf.getComponentCount() == 1) break
    }

    return if (lastEdge != null) input[lastEdge.from].x.toLong() * input[lastEdge.to].x.toLong()
    else 0L
}

data class DayEightPoint(val x: Int, val y: Int, val z: Int)

private data class Edge(val from: Int, val to: Int, val distance: Double)

private infix fun DayEightPoint.distanceTo(other: DayEightPoint): Double = sqrt(
    (other.x - this.x).toDouble().pow(2)
            + (other.y - this.y).toDouble().pow(2)
            + (other.z - this.z).toDouble().pow(2)
)

private class UnionFind(size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }
    private var componentCount = size

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int): Boolean {
        val rootX = find(x)
        val rootY = find(y)

        if (rootX == rootY) return false

        when {
            rank[rootX] < rank[rootY] -> parent[rootX] = rootY
            rank[rootX] > rank[rootY] -> parent[rootY] = rootX
            else -> {
                parent[rootY] = rootX
                rank[rootX]++
            }
        }
        componentCount--
        return true
    }

    fun getComponentCount(): Int = componentCount
}
