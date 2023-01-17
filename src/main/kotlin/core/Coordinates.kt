package core

import kotlin.math.abs

data class Coordinates (val x: Int, val y: Int) {
    fun move(coordinates: Coordinates): Coordinates {
        return Coordinates(this.x + coordinates.x, this.y + coordinates.y)
    }

    fun manhattanDistance(coordinates: Coordinates): Int {
        return abs(x - coordinates.x) + abs(y - coordinates.y)
    }
}