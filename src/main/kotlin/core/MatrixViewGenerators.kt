package core

class MatrixViewGenerators(
    val startLocation: Coordinates,
    val primaryDirection: Direction,
    val secondaryDirection: Direction,
    val view: View
) {
    companion object {
        fun getGenerators(matrix: List<List<*>>): List<MatrixViewGenerators> {
            if (matrix.isEmpty()) return listOf()

            val numRows = matrix.size
            val numCols = matrix[0].size
            return listOf(
                MatrixViewGenerators(
                    Coordinates(0, 0),
                    Direction.DOWN,
                    Direction.RIGHT,
                    View.WEST
                ),
                MatrixViewGenerators(
                    Coordinates(0, numCols - 1),
                    Direction.DOWN,
                    Direction.LEFT,
                    View.EAST
                ),
                MatrixViewGenerators(
                    Coordinates(0, 0),
                    Direction.RIGHT,
                    Direction.DOWN,
                    View.NORTH
                ),
                MatrixViewGenerators(
                    Coordinates(numRows - 1, 0),
                    Direction.RIGHT,
                    Direction.UP,
                    View.SOUTH
                )
            )
        }
    }

}