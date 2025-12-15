package net.raphdf201.adventofcode2025

import kotlin.math.sqrt

fun dayEightPartOne(input: List<String>): Int {
    val points = input.map {
        val (x, y, z) = it.split(",")
        Point(x.toInt(), y.toInt(), z.toInt())
    }

    val edges = mutableListOf<Edge>()
    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            edges.add(Edge(i, j, points[i] distanceTo points[j]))
        }
    }

    edges.sortBy { it.distance }

    val uf = UnionFind(points.size)

    val edgesToTry = minOf(1000, edges.size)
    for (i in 0 until edgesToTry) {
        val edge = edges[i]
        uf.union(edge.from, edge.to)
    }

    val circuitSizes = mutableMapOf<Int, Int>()
    for (i in points.indices) {
        val root = uf.find(i)
        circuitSizes[root] = (circuitSizes[root] ?: 0) + 1
    }

    val sizes = circuitSizes.values.sortedDescending()

    return sizes[0] * sizes[1] * sizes[2]
}

fun dayEightPartTwo(input: List<String>): Long {  // Changed to Long
    val points = input.map {
        val (x, y, z) = it.split(",")
        Point(x.toInt(), y.toInt(), z.toInt())
    }

    // Create all possible edges with their distances
    val edges = mutableListOf<Edge>()
    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            edges.add(Edge(i, j, points[i] distanceTo points[j]))
        }
    }

    // Sort edges by distance
    edges.sortBy { it.distance }

    // Union-Find to track circuits
    val uf = UnionFind(points.size)

    // Keep connecting until all points are in one circuit
    var lastEdge: Edge? = null
    for (edge in edges) {
        // Try to union - if successful, this was a meaningful connection
        if (uf.union(edge.from, edge.to)) {
            lastEdge = edge

            // Check if we're done (all in one circuit)
            if (uf.getComponentCount() == 1) {
                break
            }
        }
    }

    // Multiply the X coordinates of the last two junction boxes connected
    return if (lastEdge != null) {
        points[lastEdge.from].x.toLong() * points[lastEdge.to].x.toLong()
    } else {
        0L
    }
}

private data class Point(val x: Int, val y: Int, val z: Int)

private data class Edge(val from: Int, val to: Int, val distance: Double)

private infix fun Point.distanceTo(other: Point): Double = sqrt(
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
