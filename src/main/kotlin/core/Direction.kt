package core

enum class Direction(val step: Coordinates) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    ;

    constructor(x: Int, y: Int): this(Coordinates(x, y))

    companion object {
        val CARDINALS = listOf(UP, RIGHT, DOWN, LEFT)
    }
}