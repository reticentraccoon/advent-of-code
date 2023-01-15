package aoc.y2022.day8

import java.io.File
import kotlin.math.max

fun findVisibleTrees(pathname: String): Int {
    val stringsFromFile = File(pathname).readLines()
    val numericMatrix = stringsFromFile.map {
        it.toCharArray().map { c -> c.toString().toInt() }.toIntArray()
    }.toTypedArray()
    val numRows = numericMatrix.size
    val numCols = numericMatrix[0].size

    val maxMatrixMap =
        Cardinals.values().associateWith { Array(numericMatrix.size) { Array(numericMatrix[0].size) { 0 } } }

    for (i in 0 until numRows) {
        var maxForIteration = -1
        for (j in 0 until numCols) {
            maxMatrixMap[Cardinals.LEFT]!![i][j] = maxForIteration
            if (numericMatrix[i][j] > maxForIteration) {
                maxForIteration = numericMatrix[i][j]
            }
        }
    }

    for (i in 0 until numRows) {
        var maxForIteration = -1
        for (j in numCols-1 downTo 0) {
            maxMatrixMap[Cardinals.RIGHT]!![i][j] = maxForIteration
            if (numericMatrix[i][j] > maxForIteration) {
                maxForIteration = numericMatrix[i][j]
            }
        }
    }

    for (i in 0 until numCols) {
        var maxForIteration = -1
        for (j in 0 until numRows) {
            maxMatrixMap[Cardinals.TOP]!![j][i] = maxForIteration
            if (numericMatrix[j][i] > maxForIteration) {
                maxForIteration = numericMatrix[j][i]
            }
        }
    }

    for (i in 0 until numCols) {
        var maxForIteration = -1
        for (j in numRows-1 downTo 0) {
            maxMatrixMap[Cardinals.BOTTOM]!![j][i] = maxForIteration
            if (numericMatrix[j][i] > maxForIteration) {
                maxForIteration = numericMatrix[j][i]
            }
        }
    }

    var visibleTrees = 0
    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            cardinals@ for (direction in Cardinals.values()) {
                if (numericMatrix[i][j] > maxMatrixMap[direction]!![i][j]) {
                    visibleTrees += 1
                    break@cardinals
                }
            }
        }
    }

    return visibleTrees
}

fun findMaxScenicScore(pathname: String): Int {
    val stringsFromFile = File(pathname).readLines()
    val numericMatrix = stringsFromFile.map {
        it.toCharArray().map { c -> c.toString().toInt() }.toIntArray()
    }.toTypedArray()
    val numRows = numericMatrix.size
    val numCols = numericMatrix[0].size

    var maxScenicDistance = 0
    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            var topIterator = i - 1
            var topDistance = 0
            top@ while (topIterator >= 0) {
                if (topIterator == 0 || numericMatrix[topIterator][j] >= numericMatrix[i][j]) {
                    topDistance = i - topIterator
                    break@top
                }
                topIterator -= 1
            }

            var bottomIterator = i + 1
            var bottomDistance = 0
            bottom@ while (bottomIterator < numRows) {
                if (bottomIterator == numRows - 1 || numericMatrix[bottomIterator][j] >= numericMatrix[i][j]) {
                    bottomDistance = bottomIterator - i
                    break@bottom
                }
                bottomIterator += 1
            }

            var leftIterator = j - 1
            var leftDistance = 0
            left@ while (leftIterator >= 0) {
                if (leftIterator == 0 || numericMatrix[i][leftIterator] >= numericMatrix[i][j]) {
                    leftDistance = j - leftIterator
                    break@left
                }
                leftIterator -= 1
            }

            var rightIterator = j + 1
            var rightDistance = 0
            right@ while (rightIterator < numCols) {
                if (rightIterator == numRows - 1 || numericMatrix[i][rightIterator] >= numericMatrix[i][j]) {
                    rightDistance = rightIterator - j
                    break@right
                }
                rightIterator += 1
            }

            val scenicNumber = topDistance * rightDistance * bottomDistance * leftDistance
            maxScenicDistance = max(maxScenicDistance, scenicNumber)
        }
    }
    return maxScenicDistance
}

enum class Cardinals {
    TOP,
    RIGHT,
    BOTTOM,
    LEFT
}

private fun printMatrix(matrix: Array<Array<Int>>) {
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            print(matrix[i][j])
        }
        println()
    }
}