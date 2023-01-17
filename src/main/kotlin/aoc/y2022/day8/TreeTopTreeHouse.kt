package aoc.y2022.day8

import core.Coordinates
import core.Direction
import core.MatrixViewGenerators
import java.io.File
import kotlin.math.max

fun findVisibleTrees(pathname: String): Int {
    val matrix = File(pathname).readLines().map {
        it.map { c -> c.digitToInt() }
    }
    val numRows = matrix.size
    val numCols = matrix[0].size

    val viewMatrices = MatrixViewGenerators.getGenerators(matrix)
        .associate { generator ->
            val viewMatrix = Array(matrix.size) { Array(matrix[0].size) { 0 } }
            generateSequence(generator.startLocation) { it.move(generator.primaryDirection.step) }
                .takeWhile { it.x in 0 until numRows && it.y in 0 until numCols }
                .forEach { rowStart ->
                    generateSequence(rowStart) { it.move(generator.secondaryDirection.step) }
                        .takeWhile { it.x in 0 until numRows && it.y in 0 until numCols }
                        .fold(-1) { maxTillNow, height ->
                            viewMatrix[height.x][height.y] = maxTillNow
                            max(maxTillNow, matrix[height.x][height.y])
                        }
                }
            generator.view to viewMatrix
    }

    return matrix.mapIndexed { i, row ->
        row.mapIndexed { j, height ->
            viewMatrices.map {
                height > it.value[i][j]
            }.reduce { acc, i -> acc || i }.let { if (it) 1 else 0 }
        }
    }.sumOf { it.sum() }
}

fun findMaxScenicScore(pathname: String): Int {
    val matrix = File(pathname).readLines().map {
        it.map { c -> c.digitToInt() }
    }
    val numRows = matrix.size
    val numCols = matrix[0].size

    val scenicScores = matrix.mapIndexed { i, row ->
        row.mapIndexed { j, height ->
            Direction.CARDINALS.map { direction ->
                val currentTree = Coordinates(i, j)
                generateSequence(currentTree) { it.move(direction.step) }
                    .drop(1)
                    .takeWhile {
                        (it.x in 0 until numRows && it.y in 0 until numCols)
                                && matrix[it.x][it.y] < height
                    }
                    .lastOrNull()
                    ?.let {
                        getScenicDistance(it, currentTree, direction.step, numRows, numCols)
                    } ?: getScenicDistance(currentTree, currentTree, direction.step, numRows, numCols)
            }.reduce { acc, i -> acc * i }
        }
    }

    return scenicScores.maxOf { it.max() }
}

private fun getScenicDistance(
    lastTreeInRange: Coordinates,
    startingElement: Coordinates,
    moveStep: Coordinates,
    numRows: Int,
    numCols: Int
): Int {
    val nextElementAfterMove = lastTreeInRange.move(moveStep)
    return if (nextElementAfterMove.x !in 0 until numRows || nextElementAfterMove.y !in 0 until numCols) {
        lastTreeInRange.manhattanDistance(startingElement)
    } else {
        lastTreeInRange.manhattanDistance(startingElement) + 1
    }
}