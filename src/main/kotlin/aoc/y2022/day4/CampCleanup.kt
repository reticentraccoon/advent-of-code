package aoc.y2022.day4

import java.io.File

fun findCompleteOverlaps(inputPathname: String): Int {
    return File(inputPathname).useLines { lines ->
        lines.map {
            val bounds = lineToBounds(it)

            (bounds[0][0] >= bounds[1][0] && bounds[0][1] <= bounds[1][1])
                || (bounds[1][0] >= bounds[0][0] && bounds[1][1] <= bounds[0][1])
        }.count { it }
    }
}

fun findPartialOverlaps(inputPathname: String): Int {
    return File(inputPathname).useLines { lines ->
        lines.map {
            val bounds = lineToBounds(it)

            !((bounds[0][0] < bounds[1][0] && bounds[0][1] < bounds[1][0])
                    || (bounds[0][0] > bounds[1][1] && bounds[0][1] > bounds[1][1]))
        }.count { it }
    }
}

fun lineToBounds(line: String): List<List<Int>> {
    return line.split(",")
        .flatMap { range -> range.split("-").map { it.toInt() } }
        .chunked(2)
}